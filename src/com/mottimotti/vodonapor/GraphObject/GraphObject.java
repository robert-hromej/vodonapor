package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public abstract class GraphObject extends View {

    public GraphParams params;

    private int offsetX, offsetY;

    public GraphObject(Context context) {
        super(context);
        setParams(new GraphParams(0, 0, 100, 100));
    }

    public GraphObject(Context context, GraphParams params) {
        super(context);
        setParams(params);
    }

    public void setParams(GraphParams params) {
        this.params = params;
        refreshLayoutParams();
    }

    private void refreshLayoutParams() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(params.width, params.height);

        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        layoutParams.leftMargin = params.x;
        layoutParams.topMargin = params.y;

        setLayoutParams(layoutParams);
    }
}
