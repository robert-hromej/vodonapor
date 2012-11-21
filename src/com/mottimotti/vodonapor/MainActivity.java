package com.mottimotti.vodonapor;

import android.app.Activity;
import android.os.Bundle;
import com.mottimotti.vodonapor.GraphObject.GraphObject;
import com.mottimotti.vodonapor.GraphObject.GraphParams;
import com.mottimotti.vodonapor.GraphObject.GraphType;
import com.mottimotti.vodonapor.controllers.DocumentPlot;
import com.mottimotti.vodonapor.controllers.InfoBox;
import com.mottimotti.vodonapor.controllers.RightPanel;
import com.mottimotti.vodonapor.controllers.Toolbar;
import com.mottimotti.vodonapor.util.LayerPosition;

import java.util.Random;

public class MainActivity extends Activity implements RightPanel.OnAddListener {

    private Toolbar toolbar;
    private InfoBox infoBox;
    private DocumentPlot plot;
    private RightPanel rightPanel;

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
        rightPanel.setDocumentPlot(plot);
        rightPanel.setOnAddListener(this);

        fillFakeDocument();
    }

    private void fillFakeDocument() {
        Random r = new Random();
        GraphParams params;

        for (int i = 0; i < 5; i++) {
            params = new GraphParams(r.nextInt(15) * 50, r.nextInt(15) * 50, r.nextInt(15) * 10, r.nextInt(15) * 10);
            plot.addGraphObject(new GraphObject(this, params, GraphType.BlueTriangle));

            params = new GraphParams(r.nextInt(15) * 50, r.nextInt(15) * 50, r.nextInt(15) * 10, r.nextInt(15) * 10);
            plot.addGraphObject(new GraphObject(this, params, GraphType.YellowRect));

            params = new GraphParams(r.nextInt(15) * 50, r.nextInt(15) * 50, r.nextInt(15) * 10, r.nextInt(15) * 10);
            plot.addGraphObject(new GraphObject(this, params, GraphType.GreenCircle));
        }
    }

    @Override
    public void onAdd(GraphObject graphObject) {
        plot.addGraphObjectToCenter(graphObject);
        plot.changeSelected(graphObject);
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
    }

}
