package com.mottimotti.vodonapor.controllers;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.mottimotti.vodonapor.commands.ICommand;
import com.mottimotti.vodonapor.commands.MoveCommand;
import com.mottimotti.vodonapor.commands.ResizeCommand;
import com.mottimotti.vodonapor.views.Graph;
import com.mottimotti.vodonapor.views.GraphParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Document extends RelativeLayout {

    private int currentX, currentY;

    public Graph selected;

    private final List<Graph> graphs = new ArrayList<Graph>();

    private Listener listener;

    public Document(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addGraphObject(int index, Graph graph) {
        graphs.add(index, graph);
        graph.setOnTouchListener(new GraphOnTouchListener());
        for (Graph child : graphs) {
            removeView(child);
            addView(child);
        }
    }

    public void addGraphObject(Graph graph) {
        graphs.add(graph);
        addView(graph);

        graph.setOnTouchListener(new GraphOnTouchListener());
    }

    public Point getViewCenter() {
        final int x = getWidth() / 2 + getScrollX();
        final int y = getHeight() / 2 + getScrollY();
        return new Point(x, y);
    }

    public void changePosition(int fromIndex, int toIndex) {
        Graph graph = graphs.remove(fromIndex);
        graphs.add(toIndex, graph);

        for (Graph child : graphs) {
            removeView(child);
            addView(child);
        }
    }

    public int size() {
        return graphs.size();
    }

    public boolean isSelectedFront() {
        return graphs.indexOf(selected) == graphs.size() - 1;
    }

    public boolean isSelectedBack() {
        return graphs.indexOf(selected) == 0;
    }

    public void load(JSONObject json) throws JSONException {
        listener.onSelect(null);

        removeAllViews();

        graphs.clear();

        JSONArray array = json.getJSONArray("GraphObjects");

        for (int i = 0; i < array.length(); i++) {
            addGraphObject(Graph.parse(getContext(), array.getJSONObject(i)));
        }

    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        for (Graph child : graphs) jsonArray.put(child.toJson());
        json.put("GraphObjects", jsonArray);

        return json;
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

    public void changeSelected(Graph selected) {
        if (this.selected != null) this.selected.setSelectedState(false);
        this.selected = selected;
        if (this.selected != null) this.selected.setSelectedState(true);
        listener.onSelect(selected);
    }

    public void removeGraphObject(Graph graph) {
        graphs.remove(graph);
        removeView(graph);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public int indexOfSelected() {
        return graphs.indexOf(selected);
    }

    private class GraphOnTouchListener implements View.OnTouchListener {
        private GraphParams originalParams;

        private int startX;
        private int startY;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            Graph graph = (Graph) view;

            if (selected != graph) changeSelected(graph);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    graph.updateMagnet(graphs);

                    startX = (int) event.getRawX();
                    startY = (int) event.getRawY();

                    originalParams = graph.params.copy();

                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    int x2 = startX - (int) event.getRawX();
                    int y2 = startY - (int) event.getRawY();

                    if (Toolbar.getTouchMode() == Toolbar.TouchMode.RESIZE)
                        graph.resizeTo(originalParams.width - x2, originalParams.height - y2);

                    if (Toolbar.getTouchMode() == Toolbar.TouchMode.MOVE)
                        graph.moveTo(originalParams.x - x2, originalParams.y - y2);

                    listener.onChangeParams(graph);

                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if (startX == (int) event.getRawX() && startY == (int) event.getRawY()) break;

                    ICommand command;

                    if (Toolbar.getTouchMode() == Toolbar.TouchMode.RESIZE) {
                        command = new ResizeCommand(graph, originalParams, graph.params.copy());
                    } else {
                        command = new MoveCommand(graph, originalParams, graph.params.copy());
                    }

                    listener.onCompleteChange(command);

                    break;
                }
            }

            return true;
        }
    }

    public static interface Listener {
        void onSelect(Graph graph);

        void onChangeParams(Graph graph);

        void onCompleteChange(ICommand command);
    }
}
