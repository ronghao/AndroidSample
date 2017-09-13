package com.haohaohu.androidsample.activity.actanim;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.haohaohu.androidsample.R;

public class ActAnim2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2_anim);
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.act_anim2_open_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActAnim2Activity.this, ActAnim2Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.act_anim2_close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

        //overridePendingTransition(R.anim.right_to_center, R.anim.center_to_left);
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.left_to_center, R.anim.center_to_right);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        //overridePendingTransition(R.anim.right_to_center, R.anim.center_to_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        //overridePendingTransition(R.anim.right_to_center, R.anim.center_to_left);
    }
}
