package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.commands.CommandManager;
import com.mottimotti.vodonapor.util.LayerPosition;
import com.mottimotti.vodonapor.views.Graph;

public class Toolbar extends LinearLayout implements View.OnClickListener {

    private static final float HIDE_ALPHA = 0.4f;

    private static TouchMode touchMode;

    private static MagnetMode magnetMode;

    private View openBtn, saveBtn, redoBtn, undoBtn, copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn, moveBtn, resizeBtn, magnetBtn;

    private Listener listener;

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.toolbar, this);

        saveBtn = findViewById(R.id.saveBtn);
        openBtn = findViewById(R.id.openBtn);
        redoBtn = findViewById(R.id.redoBtn);
        undoBtn = findViewById(R.id.undoBtn);
        copyBtn = findViewById(R.id.copyBtn);
        moveBackBtn = findViewById(R.id.moveBackBtn);
        moveBackwardBtn = findViewById(R.id.moveBackwardsBtn);
        moveForwardBtn = findViewById(R.id.moveForwardsBtn);
        moveFrontBtn = findViewById(R.id.moveFrontBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        moveBtn = findViewById(R.id.moveBtn);
        resizeBtn = findViewById(R.id.resizeBtn);
        magnetBtn = findViewById(R.id.magnetBtn);

        View[] allButtons = {openBtn, saveBtn, redoBtn, undoBtn, copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn, moveBtn, resizeBtn, magnetBtn};

        for (View button : allButtons) button.setOnClickListener(this);

        update();

        setTouchMode(TouchMode.MOVE);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveBtn:
                listener.onSave();
                break;
            case R.id.openBtn:
                listener.onOpen();
                break;
            case R.id.redoBtn:
                listener.onRedo();
                break;
            case R.id.undoBtn:
                listener.onUndo();
                break;
            case R.id.moveBackBtn:
                listener.onChangePosition(LayerPosition.MOVE_BACK);
                break;
            case R.id.moveBackwardsBtn:
                listener.onChangePosition(LayerPosition.MOVE_BACKWARDS);
                break;
            case R.id.moveFrontBtn:
                listener.onChangePosition(LayerPosition.MOVE_FRONT);
                break;
            case R.id.moveForwardsBtn:
                listener.onChangePosition(LayerPosition.MOVE_FORWARDS);
                break;
            case R.id.copyBtn:
                listener.onDuplicate();
                break;
            case R.id.deleteBtn:
                listener.onDelete();
                break;
            case R.id.moveBtn:
                setTouchMode(TouchMode.MOVE);
                break;
            case R.id.resizeBtn:
                setTouchMode(TouchMode.RESIZE);
                break;
            case R.id.magnetBtn:
                changeMagnetMode();
                break;
        }
    }

    public static MagnetMode getMagnetMode() {
        if (magnetMode == null) magnetMode = MagnetMode.ON;

        return magnetMode;
    }

    public void changeMagnetMode() {
        magnetMode = (getMagnetMode() == MagnetMode.ON) ? MagnetMode.OFF : MagnetMode.ON;
        magnetBtn.setAlpha((getMagnetMode() == MagnetMode.ON) ? 1f : HIDE_ALPHA);
    }

    public static TouchMode getTouchMode() {
        return touchMode;
    }

    public void setTouchMode(TouchMode touchMode) {
        moveBtn.setAlpha((touchMode == TouchMode.MOVE) ? 1f : HIDE_ALPHA);
        resizeBtn.setAlpha((touchMode == TouchMode.RESIZE) ? 1f : HIDE_ALPHA);

        Toolbar.touchMode = touchMode;
    }

    public void update() {
        View[] views = {copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn};
        for (View view : views) view.setAlpha(HIDE_ALPHA);
    }

    public void update(Graph object) {
        View[] views = {copyBtn, deleteBtn, moveBackBtn, moveBackwardBtn, moveForwardBtn, moveFrontBtn};

        for (View view : views)
            view.setAlpha((object == null) ? HIDE_ALPHA : 1f);
    }

    public void update(Document plot) {
        final float alpha1 = plot.isSelectedBack() ? HIDE_ALPHA : 1f;
        final float alpha2 = plot.isSelectedFront() ? HIDE_ALPHA : 1f;

        moveBackBtn.setAlpha(alpha1);
        moveBackwardBtn.setAlpha(alpha1);

        moveFrontBtn.setAlpha(alpha2);
        moveForwardBtn.setAlpha(alpha2);
    }

    public void update(CommandManager commandManager) {
        final int state = commandManager.getState();

        undoBtn.setAlpha((state == CommandManager.ALL || state == CommandManager.ONLY_UNDO) ? 1f : HIDE_ALPHA);
        redoBtn.setAlpha((state == CommandManager.ALL || state == CommandManager.ONLY_REDO) ? 1f : HIDE_ALPHA);
    }

    public static interface Listener {
        void onChangePosition(LayerPosition layerPosition);

        void onDuplicate();

        void onDelete();

        void onSave();

        void onOpen();

        void onRedo();

        void onUndo();
    }

    public static enum TouchMode {
        MOVE,
        RESIZE
    }

    public static enum MagnetMode {
        ON, OFF
    }
}
