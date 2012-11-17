package com.mottimotti.vodonapor.GraphObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Nasos extends GraphObject {

    public Nasos(Context context) {
        super(context);
    }

    public Nasos(Context context, GraphParams params) {
        super(context, params);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.e("sdf", "df");
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, this.getHeight() / 4, paint);
    }
}
