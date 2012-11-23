package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.views.Graph;

public class InfoPanel extends LinearLayout {
    public InfoPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.info_panel, this);
    }

    public void update(Graph graph) {
        TextView textView = (TextView) findViewById(R.id.infoTextView);

        if (graph == null) {
            textView.setText(null);
        } else {
            textView.setText(graph.getType().name() + "(" + graph.getX() + ", " + graph.getY() + ", "
                    + graph.getWidth() + ", " + graph.getHeight() + ")");
        }
    }
}
