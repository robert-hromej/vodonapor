package com.mottimotti.vodonapor.util;

import com.mottimotti.vodonapor.GraphObject.GraphObject;

import java.util.ArrayList;
import java.util.List;

public class Magnet {

    private List<Integer> listX;

    private List<Integer> listY;

    private GraphObject currentObject;

    public Magnet(GraphObject object, List<GraphObject> children) {
        this.currentObject = object;
        setListX(children);
        setListY(children);
    }

    public void setListX(List<GraphObject> objects) {
        this.listX = new ArrayList<Integer>();

        for (GraphObject object : objects) {
            if (currentObject != object) {
                listX.add(object.getLeftX());
                listX.add(object.getRightX());
            }
        }
    }

    public void setListY(List<GraphObject> objects) {
        this.listY = new ArrayList<Integer>();

        for (GraphObject object : objects) {
            if (currentObject != object) {
                listY.add(object.getTopY());
                listY.add(object.getBottomY());
            }
        }
    }

    public int updateX(int x) {
        Integer neighborhood = getNeighborhoodX();
        return (Math.abs(x - neighborhood) < 10) ? neighborhood : x;
    }

    public int updateY(int y) {
        Integer neighborhood = getNeighborhoodY();
        return (Math.abs(y - neighborhood) < 10) ? neighborhood : y;
    }

    private Integer getNeighborhoodX() {
        Integer resultX = null;
        Integer minD = null;
        Integer d;

        for (Integer x : listX) {

            d = Math.abs(x - currentObject.getLeftX());
            if (minD == null || minD > d) {
                minD = d;
                resultX = x;
            }

            d = Math.abs(x - currentObject.getRightX());
            if (minD == null || minD > d) {
                minD = d;
                resultX = x - currentObject.params.width;
            }
        }

        return resultX;
    }

    private Integer getNeighborhoodY() {
        Integer resultY = null;
        Integer minD = null;
        Integer d;

        for (Integer y : listY) {

            d = Math.abs(y - currentObject.getTopY());
            if (minD == null || minD > d) {
                minD = d;
                resultY = y;
            }

            d = Math.abs(y - currentObject.getBottomY());
            if (minD == null || minD > d) {
                minD = d;
                resultY = y - currentObject.params.height;
            }
        }

        return resultY;
    }
}
