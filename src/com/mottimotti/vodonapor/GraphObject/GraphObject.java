package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;
import com.mottimotti.vodonapor.controllers.Toolbar;
import com.mottimotti.vodonapor.util.Magnet;

import java.util.List;

public abstract class GraphObject extends View {

    public GraphParams params;

    private Magnet magnet;

    private boolean selectedState = false;

    public GraphObject(Context context) {
        super(context);
        setParams(new GraphParams(0, 0, 100, 100));
    }

    public GraphObject(Context context, GraphParams params) {
        super(context);
        setParams(params);
    }

    public abstract GraphObject copy();

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
        return params.y + params.height;
    }

    public void moveTo(int x, int y) {
        if (Toolbar.getMagnetMode() == Toolbar.MagnetMode.ON) {
            x = magnet.updateX(x);
            y = magnet.updateX(y);
        }

        params.x = x;
        params.y = y;

        setX(params.x);
        setY(params.y);
    }

    public void resizeTo(int width, int height) {
        if (Toolbar.getMagnetMode() == Toolbar.MagnetMode.ON) {
            width = magnet.updateWidth(width);
            height = magnet.updateHeight(height);
        }

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
            paint.setStrokeWidth(3);
            paint.setAntiAlias(true);
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, 10, 10, paint);
            canvas.drawRect(getWidth() - 10, 0, getWidth(), 10, paint);
            canvas.drawRect(0, getHeight() - 10, 10, getHeight(), paint);
            canvas.drawRect(getWidth() - 10, getHeight() - 10, getWidth(), getHeight(), paint);
        }
    }

    public void updateMagnet(List<GraphObject> objects) {
        this.magnet = new Magnet(this, objects);
    }

    public static interface Listener {
        void onSelect(GraphObject object);

        void onMove(GraphObject object);
    }
}
