package com.mottimotti.vodonapor.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.mottimotti.vodonapor.GraphObject.GraphObject;

import java.util.ArrayList;
import java.util.List;

public class DocumentPlot extends RelativeLayout {

    private int currentX;
    private int currentY;

    private GraphObject selected;

    private List<GraphObject> children;

    public DocumentPlot(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addGraphObject(GraphObject graphObject) {
        if (children == null) children = new ArrayList<GraphObject>();

        children.add(graphObject);
        addView(graphObject);
        graphObject.setOnTouchListener(new ChildOnTouchListener());
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

    private class ChildOnTouchListener implements View.OnTouchListener {

        private int currentX;
        private int currentY;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            GraphObject graph = (GraphObject) view;

            if (selected != graph) {
                if (selected != null) selected.setSelectedState(false);
                selected = graph;
                graph.setSelectedState(true);
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    currentX = (int) event.getRawX();
                    currentY = (int) event.getRawY();
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    int x2 = (int) event.getRawX();
                    int y2 = (int) event.getRawY();

                    graph.moveBy(currentX - x2, currentY - y2);

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
    }
}
