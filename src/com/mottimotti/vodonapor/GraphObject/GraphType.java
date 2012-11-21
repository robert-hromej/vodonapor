package com.mottimotti.vodonapor.GraphObject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public enum GraphType {
    EMPTY {
        @Override
        public void onDrawObject(Canvas canvas, GraphObject object) {
        }
    },
    BlueTriangle {
        @Override
        public void onDrawObject(Canvas canvas, GraphObject object) {
            Paint paint = new Paint();

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            paint.setAlpha(80);
            canvas.drawPaint(paint);

            paint.setColor(Color.BLUE);

            Path path = new Path();

            path.setFillType(Path.FillType.EVEN_ODD);

            path.moveTo(object.getWidth() / 2, 10);

            path.lineTo(object.getWidth() - 10, object.getHeight() - 10);
            path.lineTo(10, object.getHeight() - 10);
            path.close();

            canvas.drawPath(path, paint);
        }
    },
    GreenCircle {
        @Override
        public void onDrawObject(Canvas canvas, GraphObject object) {
            Paint paint = new Paint();

            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(object.getWidth() / 2, object.getHeight() / 2, Math.min(object.getHeight(), object.getWidth()) / 2 - 5, paint);
        }
    },
    YellowRect {
        @Override
        public void onDrawObject(Canvas canvas, GraphObject object) {
            Paint paint = new Paint();

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);

            paint.setAntiAlias(true);
            paint.setColor(Color.YELLOW);
            canvas.drawRect(10, 10, object.getWidth() - 10, object.getHeight() - 10, paint);
        }
    };

    public abstract void onDrawObject(Canvas canvas, GraphObject object);
}
