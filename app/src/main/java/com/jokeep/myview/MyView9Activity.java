package com.jokeep.myview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by wbq501 on 2016-1-12 16:48.
 * MyView
 */
public class MyView9Activity extends Activity {

    private ImageView mScanImage;
    private ImageView mSecondImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    private static class SampleView extends View {
        private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float mRotate;
        private Matrix mMatrix = new Matrix();
        private Shader mShader;
        private boolean mDoTiming;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            float x = 160;
            float y = 100;
            mShader = new SweepGradient(x, y, new int[] { Color.GREEN,
                    Color.RED,
                    Color.BLUE,
                    Color.GREEN }, null);
            mPaint.setShader(mShader);
            mPaint.setStyle(Paint.Style.STROKE);
            PathEffect effect = new DashPathEffect(new float[] { 5, 8, 5, 8}, 1);
            mPaint.setPathEffect(effect);
            mPaint.setStrokeWidth(10);
        }

        @Override protected void onDraw(Canvas canvas) {
            Paint paint = mPaint;
            float x = 160;
            float y = 100;

            canvas.drawColor(Color.WHITE);

            mMatrix.setRotate(mRotate, x, y);
            mShader.setLocalMatrix(mMatrix);
            mRotate += 3;
            if (mRotate >= 360) {
                mRotate = 0;
            }
            invalidate();

            if (mDoTiming) {
                long now = System.currentTimeMillis();
                for (int i = 0; i < 20; i++) {
                    canvas.drawCircle(x, y, 80, paint);
                }
                now = System.currentTimeMillis() - now;
                android.util.Log.d("skia", "sweep ms = " + (now/20.));
            }
            else {
                RectF rect = new RectF(x - 80, y - 80, x + 80, y + 80);
                Paint paintRect = new Paint();
                paintRect.setColor(Color.RED);
                paintRect.setStyle(Paint.Style.STROKE);
                canvas.drawRect(rect, paintRect);
                Path path = new Path();
                path.addArc(rect, 60, 60);
                canvas.clipPath(path);
                //canvas.clipPath(path，Op.XOR);
                canvas.drawCircle(x, y, 80, paint);
            }
        }
    }
}
