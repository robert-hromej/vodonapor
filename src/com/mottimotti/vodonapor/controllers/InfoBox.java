package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mottimotti.vodonapor.GraphObject.GraphObject;
import com.mottimotti.vodonapor.R;

public class InfoBox extends LinearLayout {
    public InfoBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.info_box, this);
    }

    public void update(GraphObject object) {
        TextView textView = (TextView) findViewById(R.id.infoTextView);

        if (object == null) {
            textView.setText(null);
        } else {
            textView.setText(object.getType().name() + "(" + object.getX() + ", " + object.getY() + ", "
                    + object.getWidth() + ", " + object.getHeight() + ")");
        }
    }
}
