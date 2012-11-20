package com.mottimotti.vodonapor.GraphObject;

public class GraphParams {

    public int x;

    public int y;

    public int width;

    public int height;

    public GraphParams(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public GraphParams clone() {
        return new GraphParams(x, y, width, height);
    }
}
