package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.mottimotti.vodonapor.GraphObject.GraphObject;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.util.LayerPosition;

public class Toolbar extends LinearLayout implements View.OnClickListener {

    private static final float HIDE_ALPHA = 0.4f;

    private View copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn;

    private Listener listener;

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.toolbar, this);

        copyBtn = findViewById(R.id.copyBtn);
        moveBackBtn = findViewById(R.id.moveBackBtn);
        moveBackwardBtn = findViewById(R.id.moveBackwardsBtn);
        moveForwardBtn = findViewById(R.id.moveForwardsBtn);
        moveFrontBtn = findViewById(R.id.moveFrontBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        copyBtn.setOnClickListener(this);
        moveBackBtn.setOnClickListener(this);
        moveBackwardBtn.setOnClickListener(this);
        moveForwardBtn.setOnClickListener(this);
        moveFrontBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        updateButtons();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.moveBackBtn:
                listener.onChange(LayerPosition.MOVE_BACK);
                break;
            case R.id.moveBackwardsBtn:
                listener.onChange(LayerPosition.MOVE_BACKWARDS);
                break;
            case R.id.moveFrontBtn:
                listener.onChange(LayerPosition.MOVE_FRONT);
                break;
            case R.id.moveForwardsBtn:
                listener.onChange(LayerPosition.MOVE_FORWARDS);
                break;
            case R.id.copyBtn:
                listener.onCopy();
                break;
            case R.id.deleteBtn:
                listener.onDelete();
                break;
        }
    }

    public void updateButtons() {
        View[] views = {copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn};
        for (View view : views) view.setAlpha(HIDE_ALPHA);
    }

    public void updateButtons(GraphObject object) {
        View[] views = {copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn};

        for (View view : views)
            view.setAlpha((object == null) ? HIDE_ALPHA : 1f);
    }

    public void updateButtons(DocumentPlot plot) {
        LayerPosition layerPosition = plot.getSelectedLayerPosition();

        float alpha1 = (layerPosition == LayerPosition.MOVE_BACK) ? HIDE_ALPHA : 1f;
        float alpha2 = (layerPosition == LayerPosition.MOVE_FRONT) ? HIDE_ALPHA : 1f;

        moveBackBtn.setAlpha(alpha1);
        moveBackwardBtn.setAlpha(alpha1);

        moveFrontBtn.setAlpha(alpha2);
        moveForwardBtn.setAlpha(alpha2);
    }

    public static interface Listener {
        void onChange(LayerPosition layerPosition);

        void onCopy();

        void onDelete();
    }
}
