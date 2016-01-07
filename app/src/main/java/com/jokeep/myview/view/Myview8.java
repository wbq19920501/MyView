package com.jokeep.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jokeep.myview.R;

/**
 * Created by wbq501 on 2016-1-7 16:03.
 * MyView
 */
public class Myview8 extends View{
    private int mWidth;
    private int mHeight;
    private Context mContext;
    //刻度宽度
    private float mTikeWidth;

    //第二个弧的宽度
    private int mScendArcWidth;

    //最小圆的半径
    private int mMinCircleRadius;

    //文字矩形的宽
    private int mRectWidth;

    //文字矩形的高
    private int mRectHeight;


    //文字内容
    private String mText = "";

    //文字的大小
    private int mTextSize;

    //设置文字颜色
    private int mTextColor;
    private int mArcColor;

    //小圆和指针颜色
    private int mMinCircleColor;

    //刻度的个数
    private int mTikeCount;

    private Paint mPaint;
    public Myview8(Context context) {
        this(context, null);
    }

    public Myview8(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Myview8(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.myview8,defStyleAttr,0);
        mArcColor = a.getColor(R.styleable.myview8_arcColor, Color.parseColor("#5FB1ED"));
        mMinCircleColor = a.getColor(R.styleable.myview8_pointerColor,Color.parseColor("#C9DEEE"));
        mTikeCount = a.getInt(R.styleable.myview8_tikeCount,12);
        mTextSize = a.getDimensionPixelSize(PxUtils.spToPx(R.styleable.myview8_android_textSize,mContext),24);
        mText = a.getString(R.styleable.myview8_android_text);
        mScendArcWidth = 50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mArcColor);
        mPaint.setStrokeWidth(3);
        RectF rectF = new RectF(3,3,mWidth-3,mHeight-3);
        //最外面的圆弧
        canvas.drawArc(rectF, 145, 250, false, mPaint);

        mPaint.setStrokeWidth(30);
        RectF rectf2 = new RectF(53,53,mWidth-53,mHeight-53);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        //大圆小圆默认红色
        canvas.drawArc(rectf2, 135, 265, false, mPaint);

        mPaint.setStrokeWidth(30);
        RectF rectf3 = new RectF(53,53,mWidth-53,mHeight-53);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mArcColor);
        //大圆小圆改变色
        canvas.drawArc(rectf3, 135, 100, false, mPaint);

        mPaint.setColor(mArcColor);
        //中心外圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, 30, mPaint);

        mPaint.setColor(Color.RED);
        //中心小圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, 10,mPaint);

        mPaint.setColor(mArcColor);
        mTikeWidth = 25;
        mPaint.setStrokeWidth(3);
        //画刻度
        canvas.drawLine(mWidth/2,0,mWidth/2,mTikeWidth,mPaint);

        //旋转的角度
        float rAngle = 250f / mTikeCount;
        //右旋转刻度
        for (int i = 0; i < mTikeCount / 2; i++){
            canvas.rotate(rAngle,mWidth/2,mHeight/2);
            canvas.drawLine(mWidth/2,0,mWidth/2,mTikeWidth,mPaint);
        }
        //现在需要将将画布旋转回来
        canvas.rotate(-rAngle*mTikeCount/2,mWidth/2,mHeight/2);
        //通过旋转画布 绘制左面的刻度
        for (int i = 0; i < mTikeCount / 2; i++) {
            canvas.rotate(-rAngle, mWidth / 2, mHeight / 2);
            canvas.drawLine(mWidth / 2, 0, mWidth / 2, mTikeWidth, mPaint);
        }
        //现在需要将将画布旋转回来
        canvas.rotate(rAngle * mTikeCount / 2, mWidth / 2, mHeight / 2);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);

        canvas.rotate(5, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth/2,(106-mHeight/2)+27, mWidth/2,mHeight/2-10,mPaint);
        //将画布旋转回来
        canvas.rotate(-5, mWidth / 2, mHeight / 2);
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
            mWidth = PxUtils.dpToPx(200,mContext);
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }else {
            mHeight = PxUtils.dpToPx(200,mContext);
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
