package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.mottimotti.vodonapor.R;

public class LeftToolBar extends LinearLayout implements View.OnClickListener {

    private View content;

    public LeftToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.left_tool_bar, this);

        content = findViewById(R.id.objectList);

        findViewById(R.id.openCloseBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        content.setVisibility((content.getVisibility() == VISIBLE) ? GONE : VISIBLE);
    }
}
