package com.jokeep.myview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jokeep.myview.view.DrawView;

/**
 * Created by wbq501 on 2016-1-4 15:55.
 * MyView
 */
public class MyView6Activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview6);
        LinearLayout layout=(LinearLayout) findViewById(R.id.root);
        final DrawView view=new DrawView(this);
        view.setMinimumHeight(1000);
        view.setMinimumWidth(600);
        //通知view组件重绘
        view.invalidate();
        layout.addView(view);
    }
}
