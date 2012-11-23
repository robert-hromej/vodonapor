package com.mottimotti.vodonapor.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public enum GraphType {
    EMPTY {
        @Override
        public void onDrawObject(Canvas canvas, Graph graph) {
        }
    },
    BlueTriangle {
        @Override
        public void onDrawObject(Canvas canvas, Graph graph) {
            Paint paint = new Paint();

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            paint.setAlpha(80);
            canvas.drawPaint(paint);

            paint.setColor(Color.BLUE);

            Path path = new Path();

            path.setFillType(Path.FillType.EVEN_ODD);

            path.moveTo(graph.getWidth() / 2, 10);

            path.lineTo(graph.getWidth() - 10, graph.getHeight() - 10);
            path.lineTo(10, graph.getHeight() - 10);
            path.close();

            canvas.drawPath(path, paint);
        }
    },
    GreenCircle {
        @Override
        public void onDrawObject(Canvas canvas, Graph graph) {
            Paint paint = new Paint();

            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(graph.getWidth() / 2, graph.getHeight() / 2, Math.min(graph.getHeight(), graph.getWidth()) / 2 - 5, paint);
        }
    },
    YellowRect {
        @Override
        public void onDrawObject(Canvas canvas, Graph graph) {
            Paint paint = new Paint();

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);

            paint.setAntiAlias(true);
            paint.setColor(Color.YELLOW);
            canvas.drawRect(10, 10, graph.getWidth() - 10, graph.getHeight() - 10, paint);
        }
    };

    public void drawGraph(Canvas canvas, Graph graph) {
        onDrawObject(canvas, graph);

        if (graph.getSelectedState()) {
            Paint paint = new Paint();

            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setAntiAlias(true);
            canvas.drawRect(0, 0, graph.getWidth(), graph.getHeight(), paint);

            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, 10, 10, paint);
            canvas.drawRect(graph.getWidth() - 10, 0, graph.getWidth(), 10, paint);
            canvas.drawRect(0, graph.getHeight() - 10, 10, graph.getHeight(), paint);
            canvas.drawRect(graph.getWidth() - 10, graph.getHeight() - 10, graph.getWidth(), graph.getHeight(), paint);
        }
    }

    protected abstract void onDrawObject(Canvas canvas, Graph graph);
}
