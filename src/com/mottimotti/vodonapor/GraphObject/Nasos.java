package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Nasos extends GraphObject {

    public Nasos(Context context) {
        super(context);
    }

    public Nasos(Context context, GraphParams params) {
        super(context, params);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(10, 10, getWidth() - 10, getHeight() - 10, paint);

        super.onDraw(canvas);
    }
}
