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

//        paint.setColor(Color.GREEN);
//        canvas.drawRect(20, 200, 460, 230, paint);
//
//        int x = 310;
//        int y = 190;
//
//        paint.setColor(Color.GRAY);
//        paint.setTextSize(25);
//        String str2rotate = "Лучик солнца!";
//
//        Rect rect = new Rect();
//
//        canvas.rotate(-45, x + rect.exactCenterX(), y + rect.exactCenterY());
//
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawText(str2rotate, x, y, paint);
//
//        canvas.restore();
//
//        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setAntiAlias(true);
//        paint.setTextSize(30);
//        canvas.drawText("Лужайка для котов", canvas.getWidth() - 100, 200, paint);
    }

}
