package com.haohaohu.androidsample;

import android.app.Application;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 主application
 *
 * @author haohao on 2017/8/19 11:32
 * @version v1.0
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initialize();
    }

    private void initialize() {
        initLogger();
        Utils.init(BaseApplication.this);
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //  Whether to show thread info or not. Default true
                .tag("Sample")
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        //  Hides internal method calls up to offset. Default 5
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
