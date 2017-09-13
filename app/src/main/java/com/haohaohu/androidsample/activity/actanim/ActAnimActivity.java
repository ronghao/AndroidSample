package com.haohaohu.androidsample.activity.actanim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.haohaohu.androidsample.R;

public class ActAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_anim);
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.act_anim_open_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActAnimActivity.this, ActAnim1Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.act_anim_open1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActAnimActivity.this, ActAnim2Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.act_anim_open2_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActAnimActivity.this, ActAnim3Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.act_anim_close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
