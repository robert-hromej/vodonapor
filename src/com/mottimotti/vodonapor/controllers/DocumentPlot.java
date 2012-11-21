package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.mottimotti.vodonapor.GraphObject.GraphObject;
import com.mottimotti.vodonapor.GraphObject.GraphParams;
import com.mottimotti.vodonapor.R;
import com.mottimotti.vodonapor.util.LayerPosition;
import com.mottimotti.vodonapor.util.Magnet;

import java.util.ArrayList;
import java.util.List;

public class DocumentPlot extends RelativeLayout {

    private int currentX, currentY;

    public GraphObject selected;

    private List<GraphObject> children;

    private GraphObject.Listener listener;

    public DocumentPlot(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) inflater.inflate(R.layout.document_plot, this);

        children = new ArrayList<GraphObject>();
    }

    public void addGraphObject(GraphObject graphObject) {
        children.add(graphObject);
        addView(graphObject);

        graphObject.setOnTouchListener(new ChildOnTouchListener());
    }

    public void addGraphObjectToCenter(GraphObject object) {
        final int x = getWidth() / 2 + getScrollX() - object.params.width / 2;
        final int y = getHeight() / 2 + getScrollY() - object.params.height / 2;

        object.moveTo(x, y);

        addGraphObject(object);
    }

    public void changePosition(LayerPosition layerPosition) {
        if (layerPosition.change(children, selected)) {
            for (GraphObject child : children) {
                removeView(child);
                addView(child);
            }
        }
    }

    public LayerPosition getSelectedLayerPosition() {
        int index = children.indexOf(selected);

        if (index == 0) {
            return LayerPosition.MOVE_BACK;
        } else if (index + 1 == children.size()) {
            return LayerPosition.MOVE_FRONT;
        }

        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                currentX = (int) event.getRawX();
                currentY = (int) event.getRawY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int x2 = (int) event.getRawX();
                int y2 = (int) event.getRawY();
                scrollBy(currentX - x2, currentY - y2);
                currentX = x2;
                currentY = y2;
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        return true;
    }

    public void changeSelected(GraphObject selected) {
        if (this.selected != null) this.selected.setSelectedState(false);
        this.selected = selected;
        this.selected.setSelectedState(true);
        listener.onSelect(selected);
    }

    public void copySelectedObject() {
        if (selected == null) return;

        GraphObject newObject = selected.copy();

        addGraphObject(newObject);

        changeSelected(newObject);
    }

    public void deleteSelectedObject() {
        if (selected == null) return;

        children.remove(selected);
        removeView(selected);
        selected = null;
    }

    public void setListener(GraphObject.Listener listener) {
        this.listener = listener;
    }

    private class ChildOnTouchListener implements View.OnTouchListener {
        private GraphParams originalParams;

        private int startX;
        private int startY;

        private Magnet magnet;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            GraphObject graph = (GraphObject) view;

            if (selected != graph) changeSelected(graph);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    graph.updateMagnet(children);

                    startX = (int) event.getRawX();
                    startY = (int) event.getRawY();

                    originalParams = graph.params.copy(0, 0);

                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    int x2 = startX - (int) event.getRawX();
                    int y2 = startY - (int) event.getRawY();

                    if (Toolbar.getTouchMode() == Toolbar.TouchMode.RESIZE)
                        graph.resizeTo(originalParams.width - x2, originalParams.height - y2);

                    if (Toolbar.getTouchMode() == Toolbar.TouchMode.MOVE)
                        graph.moveTo(originalParams.x - x2, originalParams.y - y2);

                    listener.onMove(graph);

                    break;
                }
                case MotionEvent.ACTION_UP: {
                    break;
                }
            }

            return true;
        }
    }
}
