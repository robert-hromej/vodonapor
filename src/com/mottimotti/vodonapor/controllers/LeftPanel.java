package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.mottimotti.vodonapor.R;

public class LeftPanel extends LinearLayout implements View.OnClickListener {

    private final View content;

    public LeftPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.left_panel, this);

        content = findViewById(R.id.objectList);

        findViewById(R.id.openCloseBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        content.setVisibility((content.getVisibility() == VISIBLE) ? GONE : VISIBLE);
    }
}
