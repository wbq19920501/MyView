package com.jokeep.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.jokeep.myview.R;

/**
 * Created by wbq501 on 2015-12-30 16:34.
 * MyView
 */
public class MyView4 extends View{
    private Paint bPaint;
    private Paint sPaint;
    private Paint tPaint;

    private int bcolor;
    private int scolor;
    private int tcolor;
    private int bwidth;
    private int textsize = 20;

    private int progress;
    private int total = 100;

    public MyView4(Context context) {
        this(context, null);
    }
    public MyView4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MyView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.myview4,defStyleAttr,0);
        int n = a.getIndexCount();
        for (int i=0;i<n;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.myview4_bround:
                    bcolor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.myview4_sround:
                    scolor = a.getColor(attr,Color.GRAY);
                    break;
                case R.styleable.myview4_bwidth:
                    bwidth = a.getInt(attr,20);
                    break;
                case R.styleable.myview4_textcolor:
                    tcolor = a.getColor(attr,Color.WHITE);
                    break;
                case R.styleable.myview4_textsize:
                    textsize = a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        bPaint = new Paint();
        sPaint = new Paint();
        tPaint = new Paint();
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    progress++;
                    if (progress==101){
                        progress=0;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        bPaint.setAntiAlias(true);
        bPaint.setStyle(Paint.Style.STROKE);
        bPaint.setColor(bcolor);
        bPaint.setStrokeWidth(bwidth);

        sPaint.setStrokeWidth(bwidth);
        sPaint.setStyle(Paint.Style.FILL);
        sPaint.setColor(scolor);
        sPaint.setAntiAlias(true);

        tPaint.setColor(tcolor);
        tPaint.setTextSize(textsize);
        tPaint.setAntiAlias(true);
        tPaint.setStyle(Paint.Style.FILL);

        int center = getWidth()/2;
        int radius = center-bwidth/2;
        canvas.drawCircle(center,center,radius-bwidth/2,sPaint);
        if (progress>0){
            RectF rectf = new RectF();
            rectf.left = center-radius;
            rectf.top = center-radius;
            rectf.right = center+radius;
            rectf.bottom = center+radius;
            canvas.drawArc(rectf,-90,((float)progress/total) * 360, false, bPaint);
            canvas.drawText(progress+"%",center-bwidth,center+bwidth/2,tPaint);
        }
    }
}
