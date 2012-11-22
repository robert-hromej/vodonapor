package com.mottimotti.vodonapor;

import android.app.Activity;
import android.os.Bundle;
import com.mottimotti.vodonapor.GraphObject.GraphObject;
import com.mottimotti.vodonapor.controllers.DocumentPlot;
import com.mottimotti.vodonapor.controllers.InfoBox;
import com.mottimotti.vodonapor.controllers.RightPanel;
import com.mottimotti.vodonapor.controllers.Toolbar;
import com.mottimotti.vodonapor.util.DocumentManager;
import com.mottimotti.vodonapor.util.LayerPosition;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends Activity implements RightPanel.OnAddListener {

    private Toolbar toolbar;
    private InfoBox infoBox;
    private DocumentPlot plot;
    private RightPanel rightPanel;

    private DocumentManager documentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        infoBox = (InfoBox) findViewById(R.id.infoBox);
        plot = (DocumentPlot) findViewById(R.id.documentPlot);
        rightPanel = (RightPanel) findViewById(R.id.rightPanel);

        plot.setListener(new GraphListener());
        toolbar.setListener(new ToolbarListener());
        rightPanel.setOnAddListener(this);

        documentManager = new DocumentManager(getCacheDir().getParent() + "/document.json");
    }

    @Override
    public void onAdd(GraphObject graphObject) {
        plot.addGraphObjectToCenter(graphObject);
        plot.changeSelected(graphObject);
        plot.listener.onAdd();
    }

    private class GraphListener implements GraphObject.Listener {
        @Override
        public void onSelect(GraphObject object) {
            toolbar.updateButtons(object);
            infoBox.update(object);
            toolbar.updateButtons(plot);
        }

        @Override
        public void onMove(GraphObject object) {
            infoBox.update(object);
        }

        @Override
        public void onAdd() {
            try {
                documentManager.remember(plot.toJson());
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        @Override
        public void onRemove() {
            try {
                documentManager.remember(plot.toJson());
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        @Override
        public void onResize() {
            try {
                documentManager.remember(plot.toJson());
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        @Override
        public void onCompleteMove() {
            try {
                documentManager.remember(plot.toJson());
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private class ToolbarListener implements Toolbar.Listener {
        @Override
        public void onChange(LayerPosition layerPosition) {
            if (plot.selected == null) return;

            plot.changePosition(layerPosition);
            toolbar.updateButtons(plot);
        }

        @Override
        public void onCopy() {
            plot.copySelectedObject();
        }

        @Override
        public void onDelete() {
            plot.deleteSelectedObject();
            toolbar.updateButtons();
        }

        @Override
        public void onSave() {
            try {
                documentManager.remember(plot.toJson());
                documentManager.save();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onOpen() {
            try {
                JSONObject json = documentManager.open();
                if (json != null) plot.load(json);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onRedo() {
            try {
                plot.load(documentManager.redo());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUndo() {
            try {
                JSONObject json = documentManager.undo();
                if (json != null) plot.load(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
