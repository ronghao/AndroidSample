package com.haohaohu.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.haohaohu.androidsample.R;

public class LifeActivity extends AppCompatActivity {

    private static final String TAG = "LifeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        Log.w(TAG, "onCreate");
        init();
    }

    private void init() {
        findViewById(R.id.life_goto_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeActivity.this, Life2Activity.class);
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
}
