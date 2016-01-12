package com.jokeep.myview;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(this);
        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn1:
                intent = new Intent(MainActivity.this,MyView1Activity.class);
                break;
            case R.id.btn2:
                intent = new Intent(MainActivity.this,MyView2Activity.class);
                break;
            case R.id.btn3:
                intent = new Intent(MainActivity.this,MyView3Activity.class);
                break;
            case R.id.btn4:
                intent = new Intent(MainActivity.this,MyView4Activity.class);
                break;
            case R.id.btn5:
                intent = new Intent(MainActivity.this,MyView5Activity.class);
                break;
            case R.id.btn6:
                intent = new Intent(MainActivity.this,MyView6Activity.class);
                break;
            case R.id.btn7:
                intent = new Intent(MainActivity.this,MyView7Activity.class);
                break;
            case R.id.btn8:
                intent = new Intent(MainActivity.this,MyView8Activity.class);
                break;
            case R.id.btn9:
                intent = new Intent(MainActivity.this,MyView9Activity.class);
                break;
        }
        startActivity(intent);
    }
}
