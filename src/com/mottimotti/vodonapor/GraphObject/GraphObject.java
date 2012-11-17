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

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int state = e.getAction();

        int eventX = (int) e.getX();
        int eventY = (int) e.getY();

        if (state == MotionEvent.ACTION_DOWN) {
            offsetX = params.x - eventX;
            offsetY = params.y - eventY;
        } else if (state == MotionEvent.ACTION_UP) {
            int destX = eventX + offsetX;
            int destY = eventY + offsetY;
            // Collision detection?
            params.x = destX;
            params.y = destY;
            refreshLayoutParams();
        } else if (state == MotionEvent.ACTION_MOVE) {
        }
        invalidate();
        return true;
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
