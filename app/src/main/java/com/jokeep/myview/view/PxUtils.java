package com.jokeep.myview.view;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by wbq501 on 2016-1-7 16:14.
 * MyView
 */
public class PxUtils {
    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
