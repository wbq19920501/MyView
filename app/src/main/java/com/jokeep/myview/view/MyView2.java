package com.jokeep.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.jokeep.myview.R;

/**
 * Created by wbq501 on 2015-12-30 11:20.
 * MyView
 */
public class MyView2 extends View{

    private int firstcolor;
    private int twocolor;
    private int roundw;
    private int progress;
    private int mSpeed;

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    /**
     * 是否应该开始下一个
     */
    private boolean isNext = false;

    public MyView2(Context context) {
        this(context, null);
    }

    public MyView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.myview2,defStyleAttr,0);
        int n = a.getIndexCount();
        for (int i=0;i<n;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.myview2_firstcolor:
                    firstcolor = a.getColor(attr, Color.YELLOW);
                    break;
                case R.styleable.myview2_twocolor:
                    twocolor = a.getColor(attr,Color.BLUE);
                    break;
                case R.styleable.myview2_circlewidth:
                    roundw = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.myview2_speed:
                    mSpeed = a.getInt(attr,20);
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    progress++;
                    if (progress==360){
                        progress=0;
                        if (!isNext){
                            isNext = true;
                        }else {
                            isNext = false;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth()/2;//获得圆心坐标
        int radius = center-roundw/2;
        mPaint.setStrokeWidth(roundw);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectf = new RectF(center-radius,center-radius,center+radius,center+radius);
        if(!isNext){
            mPaint.setColor(firstcolor);
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环
            mPaint.setColor(twocolor); // 设置圆环的颜色
            canvas.drawArc(rectf, -90, progress, false, mPaint); // 根据进度画圆弧
        }else {
            mPaint.setColor(twocolor);
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环
            mPaint.setColor(firstcolor); // 设置圆环的颜色
            canvas.drawArc(rectf, -90, progress, false, mPaint); // 根据进度画圆弧
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
