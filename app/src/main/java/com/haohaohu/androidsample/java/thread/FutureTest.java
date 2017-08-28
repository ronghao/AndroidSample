package com.haohaohu.androidsample.java.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Future 示例
 *
 * @author haohao on 2017/8/28 9:27
 * @version v1.0
 */
public class FutureTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10));
        final Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "111\n";
            }
        });

        Future<String> future1 = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String d = future.get();
                Thread.sleep(3000);
                return "end\n";
            }
        });

        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "endend";
            }
        });

        try {
            System.out.print(System.currentTimeMillis() + "\n");
            System.out.print(future1.get());
            System.out.print(System.currentTimeMillis() + "");

            executor.submit(futureTask);
            System.out.print(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
