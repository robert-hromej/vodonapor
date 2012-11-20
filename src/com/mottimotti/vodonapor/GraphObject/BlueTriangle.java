package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class BlueTriangle extends GraphObject {

    public BlueTriangle(Context context, GraphParams params) {
        super(context, params);
    }

    public BlueTriangle(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAlpha(80);
        canvas.drawPaint(paint);

        paint.setColor(Color.BLUE);

        Path path = new Path();

        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(getWidth() / 2, 10);

        path.lineTo(getWidth() - 10, getHeight() - 10);
        path.lineTo(10, getHeight() - 10);
        path.close();

        canvas.drawPath(path, paint);

        super.onDraw(canvas);
    }
}
