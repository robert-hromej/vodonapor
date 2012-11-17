package com.mottimotti.vodonapor;

import android.app.Activity;
import android.os.Bundle;
import com.mottimotti.vodonapor.GraphObject.GraphParams;
import com.mottimotti.vodonapor.GraphObject.Nasos;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DocumentPlot plot = (DocumentPlot) findViewById(R.id.documentPlot);

        Nasos cilinder;

        cilinder = new Nasos(this, new GraphParams(50, 50, 100, 100));
        plot.addView(cilinder);

        cilinder = new Nasos(this, new GraphParams(90, 90, 150, 150));
        plot.addView(cilinder);

//        plot.invalidate();

//        cilinder = new Nasos(this, new GraphParams(300, 100, 150, 150));
//        plot.addView(cilinder);
//
//        cilinder = new Nasos(this, new GraphParams(300, 100, 150, 150));
//        plot.addView(cilinder);
    }
}
