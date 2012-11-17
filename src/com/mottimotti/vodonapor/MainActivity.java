package com.mottimotti.vodonapor;

import android.app.Activity;
import android.os.Bundle;
import com.mottimotti.vodonapor.GraphObject.GraphParams;
import com.mottimotti.vodonapor.GraphObject.Nasos;
import com.mottimotti.vodonapor.GraphObject.Tryba;
import com.mottimotti.vodonapor.ui.DocumentPlot;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DocumentPlot plot = (DocumentPlot) findViewById(R.id.documentPlot);

        plot.addGraphObject(new Nasos(this, new GraphParams(-50, 50, 100, 100)));

        plot.addGraphObject(new Nasos(this, new GraphParams(90, 90, 200, 100)));

        plot.addGraphObject(new Tryba(this, new GraphParams(130, 190, 200, 200)));
    }
}
