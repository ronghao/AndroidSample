package com.haohaohu.androidsample.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.haohaohu.androidsample.activity.ScreenActivity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class AndroidUtils {

    private static int getAndroidSdkVersionCode() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取屏幕的真实高，宽，状态栏的高，DPI四个数据
     * android系统在3.1之前通过DisplayMetrics对象获取的宽，高值为屏幕真实大小，3.2
     * 后改变为屏幕显示区域大小，且3.2，和4.x获取屏幕真实大小值得方式也发生改变 所以需要根据sdk版本来进行区分
     */
    public static HashMap<String, Object> getScreenInfos(Context mContext, WindowManager wm) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        float width = 0;
        float height = 0;
        int statusBarHeight = 0;
        float densityDpi = 0;
        float density = 0;
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        Rect rect = new Rect();
        Display display = wm.getDefaultDisplay();
        int sdkVersion = getAndroidSdkVersionCode();
        // 获取屏幕的真实宽，高，Dpi
        if (sdkVersion <= 10) {
            densityDpi = metrics.densityDpi;
            density = metrics.density;
            int w = wm.getDefaultDisplay().getWidth();
            width = metrics.widthPixels;
            height = metrics.heightPixels;
        } else if (10 < sdkVersion && sdkVersion < 13) {
            densityDpi = metrics.densityDpi;
            density = metrics.density;
            width = metrics.widthPixels;
            height = metrics.heightPixels;
        } else if (sdkVersion >= 13 && sdkVersion < 14) {
            Class<?> c1;
            Method method;
            try {
                c1 = Class.forName("android.view.Display");
                method = c1.getMethod("getRealHeight");
                height = (Integer) method.invoke(display);
                method = c1.getMethod("getRealWidth");
                width = (Integer) method.invoke(display);
                densityDpi = metrics.densityDpi;
                density = metrics.density;
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (sdkVersion >= 14) {
            Class<?> c;
            try {
                c = Class.forName("android.view.Display");
                Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, metrics);
                height = metrics.heightPixels;
                width = metrics.widthPixels;
                densityDpi = metrics.densityDpi;
                density = metrics.density;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 获取状态栏的高度
        Class<?> c2 = null;
        Object obj = null;
        Field field = null;
        try {
            c2 = Class.forName("com.android.internal.R$dimen");
            obj = c2.newInstance();
            field = c2.getField("status_bar_height");
            statusBarHeight = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = mContext.getResources().getDimensionPixelSize(statusBarHeight);
        } catch (Exception e1) {
            Log.e("", "getMessage status bar height fail");
        }

        map.put("width", width);
        map.put("height", height);
        map.put("statusBarHeight", statusBarHeight);
        map.put("densityDpi", densityDpi);
        map.put("density", density);
        map.put("scaledDensity", densityDpi / 160f);
        int statusBarHeight1 = 0;

        Rect frame = new Rect();
        if (mContext instanceof ScreenActivity) {
            ((ScreenActivity) mContext).getWindow()
                    .getDecorView()
                    .getWindowVisibleDisplayFrame(frame);
            // 状态栏高度
            statusBarHeight1 = frame.top;
        }
        map.put("statusBarHeight", statusBarHeight1);

        return map;
    }

    public static Rect getStatue(Context mContext) {
        Rect frame = new Rect();
        ((Activity) mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame;
    }
}
