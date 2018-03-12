package com.jmy.mybbsdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jmy on 2018/3/9.
 */

public class MyClockView extends View implements Runnable {
    private int rX = 100;//圆点坐标x
    private int rY = 100;//圆点坐标y
    private int x;//秒针尾x
    private int y;//秒针尾y

    private int r = 200;//圆盘半径
    private int length = 0;

    private double du = 0;//转过度数
    private int during = 1000;//停留时间
    private int zhouqi = 60000;//转完一圈周期

    private Paint sPaint;

    private int sColor;
    private double buchang;

    public MyClockView(Context context) {
        this(context, null);
    }

    public MyClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
        new Thread(this).start();
    }

    private void init() {
        buchang = 360 / (zhouqi / during);

        sPaint = new Paint();
        sColor = Color.BLACK;
        sPaint.setColor(sColor);
        sPaint.setStrokeWidth(2);
        sPaint.setStyle(Paint.Style.STROKE);
        sPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(rX, rY, x, y, sPaint);
        canvas.drawCircle(rX, rY, r, sPaint);

        Path path = new Path();
        path.moveTo(rX + (int) ((r / 2) * Math.sin(du * Math.PI / 180)), rY - (int) ((r / 2) * Math.cos(du * Math.PI / 180)));
        path.lineTo(x, y);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setColor(sColor);
        paint.setTextSize(46);
        canvas.drawTextOnPath("秒针", path, 0, 10, paint);

        int h_r = r * 9 / 10;
        int m_r = r * 19 / 20;
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(getX(r, i * 30), getY(r, i * 30), getX(h_r, i * 30), getY(h_r, i * 30), sPaint);
        }
        for (int i = 0; i < 60; i++) {
            if (i % 5 != 0)
                canvas.drawLine(getX(r, i * 6), getY(r, i * 6), getX(m_r, i * 6), getY(m_r, i * 6), sPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSize > heightSize) {
            widthSize = heightSize;
        } else {
            heightSize = widthSize;
        }
        if (widthMode != MeasureSpec.AT_MOST && widthSize > 2) {
            rX = widthSize / 2;
        }
        if (heightMode != MeasureSpec.AT_MOST && widthSize > 2) {
            rY = heightSize / 2;
        }
        if (rX < rY) {
            rY = rX;
        } else {
            rX = rY;
        }
        r = rX - 2;
        length = r * 17 / 20;
        x = rX;
        y = rY - length;
        setMeasuredDimension(rX * 2, rY * 2);
    }

    private int getSize(int mode, int size) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                break;
        }
        return size;
    }

    @Override
    public void run() {
        while (isStart) {
            try {
                Thread.sleep(during);
            } catch (InterruptedException e) {
                e.printStackTrace();
                isStart = false;
            }
            handler.sendEmptyMessage(0);
        }
    }

    private boolean isStart = true;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            du += buchang;
            if (du >= 360) {
                du = 0;
            }
            update();
        }
    };

    private void update() {
        x = rX + (int) (length * Math.sin(du * Math.PI / 180));
        y = rY - (int) (length * Math.cos(du * Math.PI / 180));
        invalidate();
    }

    private int getX(int r, int du) {
        return rX + (int) (r * Math.sin(du * Math.PI / 180));
    }

    private int getY(int r, int du) {
        return rX + (int) (r * Math.cos(du * Math.PI / 180));
    }
}
