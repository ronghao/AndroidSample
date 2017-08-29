package com.haohaohu.androidsample.activity.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.haohaohu.androidsample.R;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_thread);
        init();
        initEvent();
    }

    private void init() {
        mTextView = (TextView) findViewById(R.id.java_thread_future);
        mResultTextView = (TextView) findViewById(R.id.java_thread_result);
    }

    private void initEvent() {
        findViewById(R.id.java_thread_future).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                future();
            }
        });
    }

    public void future() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10));
        final Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                mTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        String str = mResultTextView.getText().toString();
                        mResultTextView.setText(str + "\n  3s-》future end");
                    }
                });
                return "111\n";
            }
        });

        Future<String> future1 = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String d = future.get();
                Thread.sleep(30000);
                mTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        String str = mResultTextView.getText().toString();
                        mResultTextView.setText(str + "\n  3s-》 future1 end");
                    }
                });
                return "end\n";
            }
        });

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                mTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        String str = mResultTextView.getText().toString();
                        mResultTextView.setText(str + "\n  futureTask end");
                    }
                });
                return "endend";
            }
        });

        try {
            future1.get();
            executor.submit(futureTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
