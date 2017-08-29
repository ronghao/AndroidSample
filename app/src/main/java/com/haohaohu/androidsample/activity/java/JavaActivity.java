package com.haohaohu.androidsample.activity.java;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.haohaohu.androidsample.R;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.java_future).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, ThreadActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.java_wait).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, JavaWaitActivity.class);
                startActivity(intent);
            }
        });
    }
}
