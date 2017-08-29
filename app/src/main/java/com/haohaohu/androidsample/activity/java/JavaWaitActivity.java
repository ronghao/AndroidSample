package com.haohaohu.androidsample.activity.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.haohaohu.androidsample.R;

public class JavaWaitActivity extends AppCompatActivity {

    private Integer lock = 0;

    private TextView waitView;
    private TextView notifyView;
    private TextView notifyallView;
    private TextView resultView;
    private TextView startView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_wait);
        initEvent();
    }

    private void initEvent() {

        startView = (TextView) findViewById(R.id.java_wait_start);
        waitView = (TextView) findViewById(R.id.java_wait_wait);
        notifyView = (TextView) findViewById(R.id.java_wait_notify);
        notifyallView = (TextView) findViewById(R.id.java_wait_notifyall);
        resultView = (TextView) findViewById(R.id.java_wait_result);

        startView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        waitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            synchronized (lock) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                lock++;
                                waitView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        resultView.setText(lock + "");
                                    }
                                });
                            }
                        }
                    }
                };
                thread.start();
            }
        });

        notifyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (lock) {
                    lock.notify();
                }
            }
        });
    }
}
