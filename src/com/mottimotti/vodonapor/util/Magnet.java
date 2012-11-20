package com.mottimotti.vodonapor.util;

import com.mottimotti.vodonapor.GraphObject.GraphObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Magnet {

    private HashSet<Integer> listX = new HashSet<Integer>();

    private HashSet<Integer> listY = new HashSet<Integer>();

    private GraphObject currentObject;

    public Magnet(GraphObject object, List<GraphObject> children) {
        this.currentObject = object;

        for (GraphObject child : children) {
            if (currentObject == child) continue;

            listX.add(child.getLeftX());
            listX.add(child.getRightX());
            listY.add(child.getTopY());
            listY.add(child.getBottomY());
        }
    }

    public int updateX(int x) {
        return updateCoordinate(x, listX, currentObject.getWidth());
    }

    public int updateY(int y) {
        return updateCoordinate(y, listY, currentObject.getHeight());
    }

    public int updateWidth(int width) {
        return updateSize(width, listX, currentObject.getLeftX());
    }

    public int updateHeight(int height) {
        return updateSize(height, listY, currentObject.getTopY());
    }

    private int updateSize(int size, HashSet<Integer> list, int start) {
        final int closest = closest(start + size, list);

        final int diff = Math.abs(closest - (start + size));

        return (diff < 10) ? closest - start : size;
    }

    private int updateCoordinate(int original, HashSet<Integer> list, int secondK) {
        final int firstClosest = closest(original, list);
        final int secondClosest = closest(original + secondK, list);

        final int firstDiff = Math.abs(firstClosest - original);
        final int secondDiff = Math.abs(secondClosest - original - secondK);

        if (Math.min(firstDiff, secondDiff) > 10) return original;

        return (firstDiff < secondDiff) ? firstClosest : secondClosest - secondK;
    }

    private int closest(int of, HashSet<Integer> in) {
        int min = Integer.MAX_VALUE;
        int closest = of;

        for (int v : in) {
            final int diff = Math.abs(v - of);

            if (diff < min) {
                min = diff;
                closest = v;
            }
        }

        return closest;
    }
}
