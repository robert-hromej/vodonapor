package com.mottimotti.vodonapor.util;

import com.mottimotti.vodonapor.views.Graph;
import com.mottimotti.vodonapor.views.GraphParams;

import java.util.HashSet;
import java.util.List;

public class Magnet {

    private final HashSet<Integer> listX = new HashSet<Integer>();

    private final HashSet<Integer> listY = new HashSet<Integer>();

    private final Graph graph;

    public Magnet(Graph object, List<Graph> children) {
        this.graph = object;

        GraphParams params;

        for (Graph child : children) {
            if (graph == child) continue;

            params = child.params;

            listX.add(params.getLeftX());
            listX.add(params.getRightX());
            listY.add(params.getTopY());
            listY.add(params.getBottomY());
        }
    }

    public int updateX(int x) {
        return updateCoordinate(x, listX, graph.getWidth());
    }

    public int updateY(int y) {
        return updateCoordinate(y, listY, graph.getHeight());
    }

    public int updateWidth(int width) {
        return updateSize(width, listX, graph.params.getLeftX());
    }

    public int updateHeight(int height) {
        return updateSize(height, listY, graph.params.getTopY());
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
