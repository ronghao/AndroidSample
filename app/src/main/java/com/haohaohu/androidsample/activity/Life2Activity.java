package com.haohaohu.androidsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.haohaohu.androidsample.R;

/**
 * 辅助研究生命周期
 *
 * @author haohao on 2017/8/23 22:38
 * @version v1.0
 */
public class Life2Activity extends AppCompatActivity {

    private static final String TAG = "Life2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left2);
        Log.w(TAG, "onCreate");
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
}
