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

    public GraphObject(Context context, GraphParams params) {
        super(context);
        setParams(params);
    }

    public int getLeftX() {
        return params.x;
    }

    public int getRightX() {
        return params.x + params.width;
    }

    public int getTopY() {
        return params.y;
    }

    public int getBottomY() {
        return params.x + params.height;
    }

    public void moveBy(int x, int y) {
        params.x = params.x - x;
        params.y = params.y - y;

        setX(params.x);
        setY(params.y);
    }

    public void moveTo(int x, int y) {
        params.x = x;
        params.y = y;

        setX(params.x);
        setY(params.y);
    }

    public void resizeBy(int x, int y) {
        params.width = params.width - x;
        params.height = params.height - y;
        refreshLayoutParams();
        invalidate();
    }

    public void resizeTo(int width, int height) {
        params.width = Math.max(10, width);
        params.height = Math.max(10, height);
        refreshLayoutParams();
        invalidate();
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

            paint.setStrokeWidth(10);
            canvas.drawRect(getWidth() - 10, getHeight() - 10, getWidth(), getHeight(), paint);
        }
    }



    public static interface OnSelectListener {
        void onSelect(GraphObject object);
    }

    public static interface OnMoveListener {
        void onMove(GraphObject object);
    }
}
