package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;
import com.mottimotti.vodonapor.controllers.Toolbar;
import com.mottimotti.vodonapor.util.Magnet;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GraphObject extends View {

    public GraphParams params;

    private Magnet magnet;

    private boolean selectedState = false;

    private GraphType type = GraphType.EMPTY;

    public GraphObject(Context context, GraphParams params, GraphType type) {
        super(context);

        GraphType.EMPTY.name();

        setType(type);
        setParams(params);
    }

    public GraphObject copy() {
        return new GraphObject(getContext(), params.copy(10, 10), getType());
    }

    public int getLeftX() {
        return params.x;
    }

    public int getRightX() {
        return params.x + params.width;
    }

    public int getTopY() {
        return params.y;
    }

    public int getBottomY() {
        return params.y + params.height;
    }

    public void moveTo(int x, int y) {
        if (magnet != null && Toolbar.getMagnetMode() == Toolbar.MagnetMode.ON) {
            x = magnet.updateX(x);
            y = magnet.updateX(y);
        }

        params.x = x;
        params.y = y;

        setX(params.x);
        setY(params.y);
    }

    public void resizeTo(int width, int height) {
        if (magnet != null && Toolbar.getMagnetMode() == Toolbar.MagnetMode.ON) {
            width = magnet.updateWidth(width);
            height = magnet.updateHeight(height);
        }

        params.width = Math.max(10, width);
        params.height = Math.max(10, height);
        refreshLayoutParams();
        invalidate();
    }

    public static GraphObject parse(Context context, JSONObject json) throws JSONException {
        GraphParams params = new GraphParams(json.getInt("x"), json.getInt("y"), json.getInt("width"), json.getInt("height"));
        GraphType type = GraphType.valueOf(json.getString("type"));

        return new GraphObject(context, params, type);
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();

        json.put("type", type.name());
        json.put("x", params.x);
        json.put("y", params.y);
        json.put("width", params.width);
        json.put("height", params.height);

        return json;
    }

    public void setParams(GraphParams params) {
        this.params = params;
        refreshLayoutParams();
    }

    private void refreshLayoutParams() {
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

        type.onDrawObject(canvas, this);

        if (getSelectedState()) {
            Paint paint = new Paint();

            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setAntiAlias(true);
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, 10, 10, paint);
            canvas.drawRect(getWidth() - 10, 0, getWidth(), 10, paint);
            canvas.drawRect(0, getHeight() - 10, 10, getHeight(), paint);
            canvas.drawRect(getWidth() - 10, getHeight() - 10, getWidth(), getHeight(), paint);
        }
    }

    public void updateMagnet(List<GraphObject> objects) {
        this.magnet = new Magnet(this, objects);
    }

    public GraphType getType() {
        return type;
    }

    public void setType(GraphType type) {
        this.type = type;
    }

    public static interface Listener {
        void onSelect(GraphObject object);

        void onMove(GraphObject object);

        void onAdd();

        void onRemove();

        void onResize();

        void onCompleteMove();
    }
}
