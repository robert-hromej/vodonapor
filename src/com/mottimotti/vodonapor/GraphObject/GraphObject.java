package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class GraphObject extends View {

    public GraphParams params;

    private boolean selectedState = false;

    public GraphObject(Context context) {
        super(context);
        setParams(new GraphParams(0, 0, 100, 100));
    }

    public void moveBy(int x, int y) {
        params.x = params.x - x;
        params.y = params.y - y;

        setX(params.x);
        setY(params.y);
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

        setX(params.x);
        setY(params.y);

        setLayoutParams(layoutParams);
    }

    public boolean getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(boolean selectedState) {
        this.selectedState = selectedState;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getSelectedState()) {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setAntiAlias(true);
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        }
    }

    public static interface OnSelectListener {
        void onSelect(GraphObject object);

        void onMove(GraphObject object);
    }
}
