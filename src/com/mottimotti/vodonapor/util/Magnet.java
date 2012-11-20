package com.mottimotti.vodonapor.util;

import com.mottimotti.vodonapor.GraphObject.GraphObject;

import java.util.ArrayList;
import java.util.List;

public class Magnet {

    private List<Integer> listX;

    private GraphObject currentObject;

    public Magnet(GraphObject object, List<GraphObject> children) {
        this.currentObject = object;
        setListX(children);
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

    public int updateX(int x) {
        Integer neighborhood = getNeighborhoodX();
        return (Math.abs(x - neighborhood) < 10) ? neighborhood : x;
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
}
