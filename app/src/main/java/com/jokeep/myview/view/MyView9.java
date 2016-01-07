package com.jokeep.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wbq501 on 2016-1-7 17:13.
 * MyView
 */
public class MyView9 extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public MyView9(Context context) {
        this(context, null);
    }

    public MyView9(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView9(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
        int w = 20;
        canvas.drawLine(mWidth/2,0,mWidth/2,w,mPaint);
        for (int i=0;i<25;i++){
            canvas.rotate(5,mWidth/2,mHeight/2);
            canvas.drawLine(mWidth/2,0,mWidth/2,w,mPaint);
        }
        canvas.rotate(-5 * 25, mWidth / 2, mHeight / 2);
        for (int i=0;i<25;i++){
            canvas.rotate(-5,mWidth/2,mHeight/2);
            canvas.drawLine(mWidth/2,0,mWidth/2,w,mPaint);
        }
        canvas.rotate(5*25,mWidth/2,mHeight/2);

        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, w, mPaint);
        for (int i=0;i<15;i++){
            canvas.rotate(5,mWidth/2,mHeight/2);
            canvas.drawLine(mWidth/2,0,mWidth/2,w,mPaint);
        }
        canvas.rotate(-5 * 15, mWidth / 2, mHeight / 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }else {
            mWidth = 500;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }else {
            mHeight = 500;
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
