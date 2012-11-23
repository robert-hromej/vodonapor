package com.mottimotti.vodonapor.views;

import android.graphics.Point;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphParams {

    private static final int MIN_SIZE = 20;

    public int x = 0;

    public int y = 0;

    public int width = 100;

    public int height = 100;

    public static GraphParams parse(JSONObject json) throws JSONException {
        return new GraphParams(json.getInt("x"), json.getInt("y"), json.getInt("width"), json.getInt("height"));
    }

    public GraphParams() {
    }

    public GraphParams(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Point getCoordinate() {
        return new Point(x, y);
    }

    public Point getSize() {
        return new Point(width, height);
    }

    public int getLeftX() {
        return x;
    }

    public int getRightX() {
        return x + width;
    }

    public int getTopY() {
        return y;
    }

    public int getBottomY() {
        return y + height;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();

        json.put("x", x);
        json.put("y", y);
        json.put("width", width);
        json.put("height", height);

        return json;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveBy(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public void resizeTo(int width, int height) {
        this.width = Math.max(MIN_SIZE, width);
        this.height = Math.max(MIN_SIZE, height);
    }

    public GraphParams copy() {
        return new GraphParams(x, y, width, height);
    }
}
