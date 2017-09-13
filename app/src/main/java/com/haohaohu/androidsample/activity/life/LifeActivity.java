package com.haohaohu.androidsample.activity.life;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.haohaohu.androidsample.R;
import com.orhanobut.logger.Logger;

public class LifeActivity extends AppCompatActivity {

    private static final String TAG = "生命周期";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        Logger.t(TAG).w("onCreate");
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
        Logger.t(TAG).w("onStart");
    }

    @Override

    protected void onResume() {
        super.onResume();
        Logger.t(TAG).w("onResume");
    }

    @Override
    protected void onPause() {
        Logger.t(TAG).w("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logger.t(TAG).w("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Logger.t(TAG).w("onDestroy");
        super.onDestroy();
    }

    @Override

    protected void onRestart() {
        Logger.t(TAG).w("onRestart");
        super.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Logger.t(TAG).w("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Logger.t(TAG).w("onSaveInstanceState");
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
