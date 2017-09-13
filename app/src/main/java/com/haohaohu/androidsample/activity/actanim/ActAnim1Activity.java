package com.haohaohu.androidsample.activity.actanim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.haohaohu.androidsample.R;

public class ActAnim1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1_anim);

        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.act_anim1_open_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActAnim1Activity.this, ActAnim1Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.act_anim1_close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
