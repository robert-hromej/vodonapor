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

    public void copySelectedObject() {
        if (selected == null) return;

        GraphObject newObject = selected.copy();

        addGraphObject(newObject);

        selected.setSelectedState(false);
        selected = newObject;
        selected.setSelectedState(true);
        listener.onSelect(selected);
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

        private int changeMode;

        private Magnet magnet;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            GraphObject graph = (GraphObject) view;

            if (selected != graph) {
                if (selected != null) selected.setSelectedState(false);
                selected = graph;
                graph.setSelectedState(true);
                listener.onSelect(graph);
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    magnet = new Magnet(graph, children);

                    startX = (int) event.getRawX();
                    startY = (int) event.getRawY();

                    originalParams = graph.params.copy(0, 0);

                    if (graph.getWidth() - (int) event.getX() < 10 && graph.getHeight() - (int) event.getY() < 10) {
                        changeMode = 1;
                    } else {
                        changeMode = 2;
                    }

                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    int x2 = startX - (int) event.getRawX();
                    int y2 = startY - (int) event.getRawY();

                    if (changeMode == 1) {
                        graph.resizeTo(originalParams.width - x2, originalParams.height - y2);
                    } else if (changeMode == 2) {
                        graph.moveTo(magnet.updateX(originalParams.x - x2), originalParams.y - y2);
                    }

                    listener.onMove(graph);

                    break;
                }
                case MotionEvent.ACTION_UP: {
                    changeMode = 0;
                    break;
                }
            }

            return true;
        }
    }
}
