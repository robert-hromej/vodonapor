package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.util.LayerPosition;

public class Toolbar extends LinearLayout implements View.OnClickListener {

    private OnChangeLayerPositionListener onChangeLayerPositionListener;

    private OnCopyListener onCopyListener;

    private OnDeleteListener onDeleteListener;

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.toolbar, this);

        findViewById(R.id.copyBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onCopyListener.onCopy();
            }
        });

        findViewById(R.id.deleteBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteListener.onDelete();
            }
        });

        findViewById(R.id.moveBackBtn).setOnClickListener(this);
        findViewById(R.id.moveBackwardsBtn).setOnClickListener(this);
        findViewById(R.id.moveForwardsBtn).setOnClickListener(this);
        findViewById(R.id.moveFrontBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        LayerPosition layerPosition = null;

        switch (view.getId()) {
            case R.id.moveBackBtn: {
                layerPosition = LayerPosition.MOVE_BACK;
                break;
            }
            case R.id.moveBackwardsBtn: {
                layerPosition = LayerPosition.MOVE_BACKWARDS;
                break;
            }
            case R.id.moveFrontBtn: {
                layerPosition = LayerPosition.MOVE_FRONT;
                break;
            }
            case R.id.moveForwardsBtn: {
                layerPosition = LayerPosition.MOVE_FORWARDS;
                break;
            }
        }

        onChangeLayerPositionListener.onChange(layerPosition);
    }

    public void setOnChangeLayerPositionListener(OnChangeLayerPositionListener onChangeLayerPositionListener) {
        this.onChangeLayerPositionListener = onChangeLayerPositionListener;
    }

    public void setOnCopyListener(OnCopyListener onCopyListener) {
        this.onCopyListener = onCopyListener;
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public static interface OnChangeLayerPositionListener {
        void onChange(LayerPosition layerPosition);
    }

    public static interface OnCopyListener {
        public void onCopy();
    }

    public static interface OnDeleteListener {
        public void onDelete();
    }
}
