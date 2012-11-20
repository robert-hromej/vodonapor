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

    public GraphParams copy() {
        return copy(10, 10);
    }

    public GraphParams copy(int dX, int dY) {
        return new GraphParams(x + dX, y + dY, width, height);
    }
}
