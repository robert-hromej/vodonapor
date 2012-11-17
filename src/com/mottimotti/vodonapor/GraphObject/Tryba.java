package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Tryba extends GraphObject {

    public Tryba(Context context, GraphParams params) {
        super(context, params);
    }

    public Tryba(Context context) {
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
        path.moveTo(12, 15);
        path.lineTo(getWidth() / 4, getHeight() / 8);
        path.lineTo(getWidth() / 22, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.close();

        canvas.drawPath(path, paint);

        super.onDraw(canvas);
    }
}
