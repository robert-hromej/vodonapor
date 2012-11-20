package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.util.LayerPosition;

public class Toolbar extends LinearLayout implements View.OnClickListener {

    private View copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn;

    private OnChangeLayerPositionListener onChangeLayerPositionListener;

    private OnCopyListener onCopyListener;

    private OnDeleteListener onDeleteListener;

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.toolbar, this);

        copyBtn = findViewById(R.id.copyBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        moveBackBtn = findViewById(R.id.moveBackBtn);
        moveBackwardBtn = findViewById(R.id.moveBackwardsBtn);
        moveForwardBtn = findViewById(R.id.moveForwardsBtn);
        moveFrontBtn = findViewById(R.id.moveFrontBtn);

        copyBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onCopyListener.onCopy();
            }
        });

        deleteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteListener.onDelete();
            }
        });

        moveBackBtn.setOnClickListener(this);
        moveBackwardBtn.setOnClickListener(this);
        moveForwardBtn.setOnClickListener(this);
        moveFrontBtn.setOnClickListener(this);
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

    public void showButtons() {
        View[] views = {copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn};

        for (View view : views) view.setAlpha(1f);
    }

    public void hideButtons() {
        View[] views = {copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn};

        for (View view : views) view.setAlpha(0.4f);
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
