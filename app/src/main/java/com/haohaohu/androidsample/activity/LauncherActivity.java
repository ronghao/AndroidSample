package com.haohaohu.androidsample.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.SPUtils;
import com.haohaohu.androidsample.R;
import java.util.List;

/**
 * @author haohao(ronghao3508@gmail.com) on 2018/5/3 11:24
 * @version v1.0
 */
public class LauncherActivity extends AppCompatActivity {

    private static final String TAG = "LauncherActivity";
    private Switch mSwitch;
    private TextView mValue;

    public final String startShowPath = "com.haohaohu.androidsample.activity.StartShowActivity";
    public final String startHidePath = "com.haohaohu.androidsample.activity.StartHideActivity";
    private SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        init();
    }

    private void init() {
        spUtils = new SPUtils("name");
        mSwitch = (Switch) findViewById(R.id.launcher_switch);
        mValue = (TextView) findViewById(R.id.launcher_value);

        mSwitch.setChecked(spUtils.getBoolean("show"));

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showAPP(b);
                mValue.setText(b ? "显示" : "不显示");
                Toast.makeText(LauncherActivity.this, "3秒后退出", Toast.LENGTH_SHORT).show();
                mSwitch.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LauncherActivity.this.finish();
                    }
                }, 3000);
            }
        });
    }

    private void showAPP(boolean b) {
        spUtils.put("show", b);
        if (b) {
            changeIcon(LauncherActivity.this, startShowPath);
        } else {
            changeIcon(LauncherActivity.this, startHidePath);
        }
    }

    private void changeIcon(Activity activity, String activityPath) {
        PackageManager pm = activity.getPackageManager();
        pm.setComponentEnabledSetting(activity.getComponentName(),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(activity, activityPath),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        showIm();
    }

    private void showIm() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolves = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }
    }
}
