package com.mottimotti.vodonapor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.mottimotti.vodonapor.GraphObject.GraphObject;
import com.mottimotti.vodonapor.GraphObject.GraphParams;
import com.mottimotti.vodonapor.GraphObject.Nasos;
import com.mottimotti.vodonapor.GraphObject.Tryba;
import com.mottimotti.vodonapor.ui.DocumentPlot;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener, GraphObject.OnSelectListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DocumentPlot plot = (DocumentPlot) findViewById(R.id.documentPlot);

        Random r = new Random();

        for (int i = 0; i < 20; i++){
            plot.addGraphObject(new Nasos(this, new GraphParams(r.nextInt(15) * 50, r.nextInt(15) * 50, r.nextInt(15) * 10, r.nextInt(15) * 10)));
        }

//        plot.addGraphObject(new Nasos(this, new GraphParams(-50, 50, 100, 100)));
//
//        plot.addGraphObject(new Nasos(this, new GraphParams(90, 90, 200, 100)));
//
//        plot.addGraphObject(new Tryba(this, new GraphParams(130, 190, 200, 200)));

        Button leftBtn = (Button) findViewById(R.id.leftToolBarBtn);
        leftBtn.setOnClickListener(this);

        plot.setOnSelectListener(this);
    }

    @Override
    public void onClick(View view) {
        View leftView = findViewById(R.id.leftToolBar);
        if (leftView.getVisibility() == View.GONE) {
            leftView.setVisibility(View.VISIBLE);
        } else {
            leftView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSelect(GraphObject object) {
        TextView typeLabel = (TextView) findViewById(R.id.objectTypeLabel);
        TextView xLabel = (TextView) findViewById(R.id.objectXLabel);
        TextView yLabel = (TextView) findViewById(R.id.objectYLabel);
        TextView widthLabel = (TextView) findViewById(R.id.objectWidthLabel);
        TextView heightLabel = (TextView) findViewById(R.id.objectHeightLabel);

        typeLabel.setText(object.getClass().getSimpleName());
        xLabel.setText("x: " + object.getX());
        yLabel.setText("y: " + object.getY());

        widthLabel.setText("width: " + object.getWidth());
        heightLabel.setText("height: " + object.getHeight());
    }

    @Override
    public void onMove(GraphObject object) {
        TextView typeLabel = (TextView) findViewById(R.id.objectTypeLabel);
        TextView xLabel = (TextView) findViewById(R.id.objectXLabel);
        TextView yLabel = (TextView) findViewById(R.id.objectYLabel);
        TextView widthLabel = (TextView) findViewById(R.id.objectWidthLabel);
        TextView heightLabel = (TextView) findViewById(R.id.objectHeightLabel);

        typeLabel.setText(object.getClass().getSimpleName());
        xLabel.setText("x: " + object.getX());
        yLabel.setText("y: " + object.getY());

        widthLabel.setText("width: " + object.getWidth());
        heightLabel.setText("height: " + object.getHeight());
    }
}
