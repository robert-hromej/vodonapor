package com.mottimotti.vodonapor;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import com.mottimotti.vodonapor.commands.*;
import com.mottimotti.vodonapor.controllers.Document;
import com.mottimotti.vodonapor.controllers.InfoPanel;
import com.mottimotti.vodonapor.controllers.RightPanel;
import com.mottimotti.vodonapor.controllers.Toolbar;
import com.mottimotti.vodonapor.util.DocumentManager;
import com.mottimotti.vodonapor.util.LayerPosition;
import com.mottimotti.vodonapor.views.Graph;

public class MainActivity extends Activity {

    private Toolbar toolbar;
    private InfoPanel infoPanel;
    private Document document;
    private RightPanel rightPanel;

    private DocumentManager documentManager;
    private CommandManager commandManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        infoPanel = (InfoPanel) findViewById(R.id.infoBox);
        document = (Document) findViewById(R.id.documentPlot);
        rightPanel = (RightPanel) findViewById(R.id.rightPanel);

        document.setListener(new DocumentListener());
        toolbar.setListener(new ToolbarListener());
        rightPanel.setListener(new RightPanelListener());

        documentManager = new DocumentManager(getCacheDir().getParent() + "/document.json");
        commandManager = new CommandManager();
        commandManager.setListener(new CommandListener());
    }

    private class RightPanelListener implements RightPanel.Listener {
        @Override
        public void onAdd(Graph graph) {
            Point center = document.getViewCenter();
            center.offset(-graph.params.width / 2, -graph.params.height / 2);
            graph.moveTo(center);
            ICommand command = new AddCommand(document, graph);
            commandManager.addCommand(command);
            document.changeSelected(graph);
        }
    }

    private class CommandListener implements CommandManager.Listener {
        @Override
        public void onChangeState() {
            toolbar.update(commandManager);
        }
    }

    private class DocumentListener implements Document.Listener {
        @Override
        public void onSelect(Graph graph) {
            toolbar.update(graph);
            infoPanel.update(graph);
            toolbar.update(document);
        }

        @Override
        public void onChangeParams(Graph graph) {
            infoPanel.update(graph);
        }

        @Override
        public void onCompleteChange(ICommand command) {
            commandManager.addCommand(command);
        }
    }

    private class ToolbarListener implements Toolbar.Listener {
        @Override
        public void onChangePosition(LayerPosition layerPosition) {
            if (document.selected == null) return;

            if (document.isSelectedBack() && (layerPosition == LayerPosition.MOVE_BACK || layerPosition == LayerPosition.MOVE_BACKWARDS))
                return;
            if (document.isSelectedFront() && (layerPosition == LayerPosition.MOVE_FRONT || layerPosition == LayerPosition.MOVE_FORWARDS))
                return;

            ICommand command = new ChangePositionCommand(document, layerPosition);
            commandManager.addCommand(command);
            toolbar.update(document);
        }

        @Override
        public void onDuplicate() {
            ICommand command = new DuplicateCommand(document, document.selected);
            commandManager.addCommand(command);
        }

        @Override
        public void onDelete() {
            if (document.selected == null) return;

            ICommand command = new RemoveCommand(document, document.selected, document.indexOfSelected());
            commandManager.addCommand(command);
        }

        @Override
        public void onSave() {
            documentManager.save(document);
        }

        @Override
        public void onOpen() {
            documentManager.openDocument(document);
            commandManager.resetHistory();
        }

        @Override
        public void onRedo() {
            commandManager.redo();
        }

        @Override
        public void onUndo() {
            commandManager.undo();
        }
    }
}
