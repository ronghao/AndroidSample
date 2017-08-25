package com.haohaohu.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.haohaohu.androidsample.R;
import com.orhanobut.logger.Logger;

/**
 * 主activity
 * 生命周期研究
 *
 * @author haohao on 2017/8/19 16:21
 * @version v1.0
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "onCreate");
        init();
    }

    private void init() {
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.main_goto_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.main_goto_linearlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.main_goto_rx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RXActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "onStart");
    }

    @Override

    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        Log.w(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.w(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.w(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override

    protected void onRestart() {
        Log.w(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.w(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.w(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    /**
     * API 21添加
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void post() {
        HandlerThread thread = new HandlerThread("name");
        thread.start();
        Handler handler = new Handler(thread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Logger.e("thread");
            }
        });
    }
}
