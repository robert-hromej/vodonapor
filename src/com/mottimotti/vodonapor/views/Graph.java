package com.mottimotti.vodonapor.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import android.widget.RelativeLayout;
import com.mottimotti.vodonapor.controllers.Toolbar;
import com.mottimotti.vodonapor.util.Magnet;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Graph extends View {

    public final GraphParams params;

    private Magnet magnet;

    private boolean selectedState = false;

    private GraphType type = GraphType.EMPTY;

    public Graph(Context context, GraphParams params, GraphType type) {
        super(context);

        this.type = type;
        this.params = params;

        update();
    }

    public Graph copy() {
        return new Graph(getContext(), params.copy(), getType());
    }

    public void update() {
        updateLayoutParams();
        invalidate();
    }

    public void moveTo(Point point) {
        magnet = null;
        moveTo(point.x, point.y);
    }

    public void moveTo(int x, int y) {
        if (magnet != null && Toolbar.getMagnetMode() == Toolbar.MagnetMode.ON) {
            params.moveTo(magnet.updateX(x), magnet.updateY(y));
        } else {
            params.moveTo(x, y);
        }

        update();
    }

    public void moveBy(int x, int y) {
        params.moveBy(x, y);
        update();
    }

    public void resizeTo(Point point) {
        magnet = null;
        resizeTo(point.x, point.y);
    }

    public void resizeTo(int width, int height) {
        if (magnet != null && Toolbar.getMagnetMode() == Toolbar.MagnetMode.ON) {
            params.resizeTo(magnet.updateWidth(width), magnet.updateHeight(height));
        } else {
            params.resizeTo(width, height);
        }

        update();
    }

    public static Graph parse(Context context, JSONObject json) throws JSONException {
        GraphParams params = GraphParams.parse(json);

        GraphType type = GraphType.valueOf(json.getString("type"));

        return new Graph(context, params, type);
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = params.toJson();
        json.put("type", type.name());
        return json;
    }

    private void updateLayoutParams() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(params.width, params.height);

        setX(params.x);
        setY(params.y);

        setLayoutParams(layoutParams);
    }

    public boolean getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(boolean selectedState) {
        this.selectedState = selectedState;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        type.drawGraph(canvas, this);
    }

    public void updateMagnet(List<Graph> objects) {
        this.magnet = new Magnet(this, objects);
    }

    public GraphType getType() {
        return type;
    }
}
