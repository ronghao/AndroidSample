package com.haohaohu.androidsample.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.haohaohu.androidsample.R;

/**
 * 研究ConstraintLayout布局
 *
 * @author haohao(ronghao3508@gmail.com) on 2018/2/2 上午11:29
 * @version v1.0
 */
public class ConstraintLayoutActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,
            @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrain_layout);
    }
}
