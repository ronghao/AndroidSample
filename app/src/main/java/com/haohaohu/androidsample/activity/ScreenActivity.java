package com.haohaohu.androidsample.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.haohaohu.androidsample.R;
import com.haohaohu.androidsample.util.AndroidUtils;
import java.util.Map;

/**
 * 屏幕检测
 *
 * @author haohao on 2017/9/12 下午 04:38
 * @version v1.0
 */
public class ScreenActivity extends Activity {

    private TextView dpiTextView;
    private TextView screenTextView;
    private ImageView showImageView;
    private TextView imageTextView;
    private TextView imageTextView1;
    private ImageView showImageView1;
    private TextView sizeTextView;
    private ImageView showImageView2;
    private ImageView showImageView21;
    private ImageView showImageView22;
    private TextView resultTextView;
    private ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_layout);
        initView();
    }

    private void initView() {
        dpiTextView = (TextView) findViewById(R.id.screen_textView4);
        screenTextView = (TextView) findViewById(R.id.screen_textView1);

        showImageView = (ImageView) findViewById(R.id.screen_imageView1);
        showImageView2 = (ImageView) findViewById(R.id.screen_ImageView2);
        showImageView21 = (ImageView) findViewById(R.id.screen_ImageView21);
        showImageView22 = (ImageView) findViewById(R.id.screen_ImageView22);
        imageTextView = (TextView) findViewById(R.id.screen_textView9);

        imageTextView1 = (TextView) findViewById(R.id.screen_TextView03);
        showImageView1 = (ImageView) findViewById(R.id.screen_ImageView01);

        sizeTextView = (TextView) findViewById(R.id.screen_TextView04);
        resultTextView = (TextView) findViewById(R.id.screen_TextView14);

        bgImageView = (ImageView) findViewById(R.id.screen_bg_image);

        showImageView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        bgImageView.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        bgImageView.setVisibility(View.INVISIBLE);
                        break;
                }
                return true;
            }
        });
        bgImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Map<String, Object> map = AndroidUtils.getScreenInfos(this, getWindowManager());
        screenTextView.setText(map.get("width") + "*" + map.get("height"));
        dpiTextView.setText("DPI:" + map.get("densityDpi") + "   PPI:" + map.get("scaledDensity"));
        showImageView.setBackgroundResource(R.drawable.pic);
        String str = "使用图片-hdpi适配-xhdpi适配-大小屏";
        imageTextView.setText(str
                + "\n"
                + showImageView.getWidth()
                + "*"
                + showImageView.getHeight()
                + " -- "
                + showImageView2.getWidth()
                + "*"
                + showImageView2.getHeight()
                + " -- "
                + showImageView21.getWidth()
                + "*"
                + showImageView21.getHeight()
                + " -- "
                + showImageView22.getWidth()
                + "*"
                + showImageView22.getHeight()
                + "\n所有图片尺寸为63*63"
                + "\n 第一张全dpi，第二张只有hdpi，第三张只有xhdpi，第四张在normal和large");

        imageTextView1.setText(
                showImageView1.getWidth() + "*" + showImageView1.getHeight() + "\n原始大小为72*72，hdpi");
        float width = (float) map.get("width");
        float height = (float) map.get("height");
        float dpi = (float) map.get("densityDpi");
        Integer statusBarHeight = (Integer) map.get("statusBarHeight");
        Rect rect = AndroidUtils.getStatue(ScreenActivity.this);
        sizeTextView.setText("宽："
                + width * 160.0f / dpi
                + "dp\n高："
                + height * 160.0f / dpi
                + "dp\n顶部状态栏："
                + statusBarHeight * 160.0f / dpi
                + "dp\n底部状态栏："
                + (height - rect.bottom) * 160.0f / dpi
                + "dp");

        String str1 = "结论：\n"
                + "1、图片缩放原则\n"
                + "    ldpi 0.75x\n"
                + "    mdpi baseline\n"
                + "    hdpi 1.5x\n"
                + "    xhdpi 2.0x\n"
                + "    xxhdpi 3.0x\n"
                + "2、不匹配的图片：\n"
                + "    向高清适配->图片放大\n"
                + "    向低分辨率适配->图片缩小\n"
                + "3、切图对应关系\n"
                + "    xhdpi 对应原稿的分辨率是720*1280\n"
                + "    xxhdpi对应原稿的分辨率是1080*1920\n"
                + "    large-mdpi 对应原稿的分辨率是720*1280\n"
                + "    large-hdpi 对应原稿的分辨率是1080*1920\n"
                + "    本身xhdpi的图片小，所以适配xxhpi的屏幕需要放大处理\n"
                + "4、注意宽高dp值\n"
                + "5、默认drawable的dpi为mdpi与normal\n";

        resultTextView.setText(str1);
    }
}
