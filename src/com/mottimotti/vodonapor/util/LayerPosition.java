package com.mottimotti.vodonapor.util;

import com.mottimotti.vodonapor.GraphObject.GraphObject;

import java.util.Collections;
import java.util.List;

public enum LayerPosition {

    MOVE_BACK {
        @Override
        public boolean change(List<GraphObject> list, GraphObject currentObject) {
            if (list.indexOf(currentObject) == 0) return false;

            while (MOVE_BACKWARDS.change(list, currentObject)) ;

            return true;
        }
    },

    MOVE_BACKWARDS {
        @Override
        public boolean change(List<GraphObject> list, GraphObject currentObject) {
            int index = list.indexOf(currentObject);

            if (index == 0) return false;

            Collections.swap(list, index, index - 1);

            return true;
        }
    },
    MOVE_FORWARDS {
        @Override
        public boolean change(List<GraphObject> list, GraphObject currentObject) {
            int index = list.indexOf(currentObject);

            if (index + 1 == list.size()) return false;

            Collections.swap(list, index, index + 1);

            return true;
        }
    },

    MOVE_FRONT {
        @Override
        public boolean change(List<GraphObject> list, GraphObject currentObject) {
            if (list.indexOf(currentObject) + 1 == list.size()) return false;

            while (MOVE_FORWARDS.change(list, currentObject)) ;

            return true;
        }
    };

    public abstract boolean change(List<GraphObject> list, GraphObject currentObject);
}


