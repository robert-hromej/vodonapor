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

    public int updateX(int originalX) {
        Integer neighborhood = null;
        Integer minD = null;
        Integer d;

        for (Integer x : listX) {

            d = Math.abs(x - currentObject.getLeftX());
            if (minD == null || minD > d) {
                minD = d;
                neighborhood = x;
            }

            d = Math.abs(x - currentObject.getRightX());
            if (minD == null || minD > d) {
                minD = d;
                neighborhood = x - currentObject.params.width;
            }
        }

        return (Math.abs(originalX - neighborhood) < 10) ? neighborhood : originalX;
    }

    public int updateY(int originalY) {
        Integer neighborhood = null;
        Integer minD = null;
        Integer d;

        for (Integer y : listY) {

            d = Math.abs(y - currentObject.getTopY());
            if (minD == null || minD > d) {
                minD = d;
                neighborhood = y;
            }

            d = Math.abs(y - currentObject.getBottomY());
            if (minD == null || minD > d) {
                minD = d;
                neighborhood = y - currentObject.params.height;
            }
        }

        return (Math.abs(originalY - neighborhood) < 10) ? neighborhood : originalY;
    }

    public int updateWidth(int originalWidth) {
        Integer neighborhood = null;
        Integer minD = null;
        Integer d;

        for (Integer x : listX) {

            d = Math.abs(x - currentObject.getLeftX() - originalWidth);
            if (minD == null || minD > d) {
                minD = d;
                neighborhood = x - currentObject.getLeftX();
            }
        }

        return (Math.abs(originalWidth - neighborhood) < 10) ? neighborhood : originalWidth;
    }

    public int updateHeight(int originalHeight) {
        Integer neighborhood = null;
        Integer minD = null;
        Integer d;

        for (Integer y : listY) {

            d = Math.abs(y - currentObject.getTopY() - originalHeight);
            if (minD == null || minD > d) {
                minD = d;
                neighborhood = y - currentObject.getTopY();
            }
        }

        return (Math.abs(originalHeight - neighborhood) < 10) ? neighborhood : originalHeight;
    }
}
