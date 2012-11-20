package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GreenCircle extends GraphObject {

    public GreenCircle(Context context) {
        super(context);
    }

    public GreenCircle(Context context, GraphParams params) {
        super(context, params);
    }

    @Override
    public GraphObject copy() {
        return new GreenCircle(getContext(), params.copy());
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getHeight(), getWidth()) / 2 - 5, paint);

        super.onDraw(canvas);
    }
}
