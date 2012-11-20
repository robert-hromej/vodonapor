package com.mottimotti.vodonapor;

import android.app.Activity;
import android.os.Bundle;
import com.mottimotti.vodonapor.GraphObject.*;
import com.mottimotti.vodonapor.controllers.DocumentPlot;
import com.mottimotti.vodonapor.controllers.InfoBox;
import com.mottimotti.vodonapor.controllers.Toolbar;
import com.mottimotti.vodonapor.util.LayerPosition;

import java.util.Random;

public class MainActivity extends Activity implements GraphObject.OnSelectListener, GraphObject.OnMoveListener, Toolbar.OnChangeLayerPositionListener, Toolbar.OnCopyListener, Toolbar.OnDeleteListener {

    private Toolbar toolbar;
    private InfoBox infoBox;
    private DocumentPlot plot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        infoBox = (InfoBox) findViewById(R.id.infoBox);
        plot = (DocumentPlot) findViewById(R.id.documentPlot);

        plot.setOnSelectListener(this);
        plot.setOnMoveListener(this);

        toolbar.setOnChangeLayerPositionListener(this);
        toolbar.setOnCopyListener(this);
        toolbar.setOnDeleteListener(this);
        toolbar.hideButtons();

        fillFakeDocument();
    }

    private void fillFakeDocument() {
        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            plot.addGraphObject(new YellowRect(this, new GraphParams(r.nextInt(15) * 50, r.nextInt(15) * 50, r.nextInt(15) * 10, r.nextInt(15) * 10)));
            plot.addGraphObject(new BlueTriangle(this, new GraphParams(r.nextInt(15) * 50, r.nextInt(15) * 50, r.nextInt(15) * 10, r.nextInt(15) * 10)));
            plot.addGraphObject(new GreenCircle(this, new GraphParams(r.nextInt(15) * 50, r.nextInt(15) * 50, r.nextInt(15) * 10, r.nextInt(15) * 10)));
        }
    }

    @Override
    public void onSelect(GraphObject object) {
        toolbar.showButtons();
        infoBox.update(object);
    }

    @Override
    public void onMove(GraphObject object) {
        infoBox.update(object);
    }

    @Override
    public void onChange(LayerPosition layerPosition) {
        if (plot.selected == null) return;
        plot.changePosition(plot.selected, layerPosition);
    }

    @Override
    public void onCopy() {
        plot.copySelectedObject();
    }

    @Override
    public void onDelete() {
        plot.deleteSelectedObject();
        toolbar.hideButtons();
    }
}
