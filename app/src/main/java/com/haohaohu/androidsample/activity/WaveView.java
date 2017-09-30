package com.haohaohu.androidsample.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.haohaohu.androidsample.Point;
import com.haohaohu.androidsample.Points;
import com.haohaohu.androidsample.R;
import java.util.Random;

/**
 * @author haohao(ronghao3508@gmail.com) on 2017/9/28 下午 03:08
 * @version v1.0
 */
public class WaveView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    // SurfaceHolder
    private SurfaceHolder mHolder;
    // 用于绘图的Canvas
    private Canvas mCanvas;
    // 子线程标志位
    private boolean mIsDrawing;

    private Path mPath;
    private Path mWhitePath;
    private Paint mTextPaint;
    private Paint mPaint;
    private Paint mWhitePaint;
    private Paint mTransWhitePaint;
    private Paint mBgPaint;

    private float width;
    private float height;
    private float halfWidth;
    private float halfHeight;

    private int maxsize = 4;

    private Points points = new Points();
    private Point point;
    private int index = 0;

    Random random = new Random();

    int line_1_start_color = randomColor(90);

    public WaveView(Context context) {
        super(context);
        initView();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        setLayerType(View.LAYER_TYPE_NONE, null);

        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        //mHolder.setFormat(PixelFormat.OPAQUE);

        initPaint();
    }

    private void initPaint() {
        mPath = new Path();
        mWhitePath = new Path();

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setStrokeWidth(2);
        mTextPaint.setColor(getResources().getColor(R.color.line_color));

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        mPaint.setColorFilter(new ColorMatrixColorFilter(setContrast(1.5f)));

        mWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWhitePaint.setColor(getResources().getColor(R.color.white));
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setColorFilter(new ColorMatrixColorFilter(setContrast(2f)));

        mTransWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTransWhitePaint.setStyle(Paint.Style.FILL);
        mTransWhitePaint.setAntiAlias(true);
        int[] colors = new int[] {
                ,
        };
        float[] positions = new float[] { 0f, 0.5f, 1f };
        LinearGradient gradient = new LinearGradient(100, halfHeight - 200, 100, halfHeight + 200,
                getResources().getColor(R.color.transparent),
                getResources().getColor(R.color.white), Shader.TileMode.REPEAT);
        mTransWhitePaint.setShader(gradient);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(getResources().getColor(R.color.black));
        mBgPaint.setAntiAlias(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            try {
                mCanvas = mHolder.lockCanvas();
                if (mCanvas == null) return;
                draw();
            } catch (Exception e) {
            } finally {
                if (mCanvas != null) mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    private void draw() {
        resetCanvas();

        initSize();

        //size = 100;

        addPoint();
        initPoints();

        //try {
        //    Thread.sleep(50);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }

    private void addPoint() {
        index++;
        if (index > 1000000) index = 0;

        int xoff = random.nextInt(100) - 50;
        int size = random.nextInt(maxsize) + 1;
        int color = randomColor(120);
        if (point == null) {
            point = new Point(size, xoff, color, null, null);
            points.header = point;
            points.last = point;
        } else {
            Point tmpPoint = new Point(size, xoff, color, null, null);
            points.last.next = tmpPoint;
            points.last = tmpPoint;
        }

        if (index % 5 != 0) {
            int xoff1 = random.nextInt(300) - 150;
            int size1 = random.nextInt(maxsize);
            int color1 = randomColor(120);
            Point tmpPoint1 = new Point(size1, xoff1, color1, null, null);
            points.last.next = tmpPoint1;
            points.last = tmpPoint1;
        }
    }

    private float getPointY(Point currentPoint) {
        float size = currentPoint.getYY();
        if (size == Integer.MAX_VALUE) {
            points.header = point = currentPoint.next;
            return 0;
        } else if (size == 0) {
            return 0;
        } else {
            return size;
        }
    }

    private void initPoints() {

        Point currentPoint = points.header;
        while (currentPoint != null) {
            float size = getPointY(currentPoint);
            for (int i = 0; i < 2; i++) {
                mPath.reset();
                mWhitePath.reset();
                for (int x = 0; x < width; x++) {
                    int type = i % 2 == 0 ? 1 : -1;
                    float tmpx = (x - halfWidth - currentPoint.xoff) / 60;
                    float y = getY(tmpx, type, size * 1.0f / currentPoint.y);
                    if (x == 0) {
                        mPath.moveTo(x, y);
                        mWhitePath.moveTo(x, y);
                    } else {
                        mPath.lineTo(x, y);
                        mWhitePath.lineTo(x, halfHeight + (y - halfHeight) / 1.2f);
                    }
                }
                drawBg(currentPoint.color);
            }

            currentPoint = currentPoint.next;
        }
        //for (int i = 0; i < pointSize; i++) {
        //    int sc = mCanvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
        //
        //    mPath.reset();
        //    mWhitePath.reset();
        //    for (int x = 0; x < width; x++) {
        //        int type = i % 2 == 0 ? 1 : -1;
        //        float tmpx = (x - halfWidth - xoff) / 100;
        //        float y = getY(tmpx, type);
        //        if (x == 0) {
        //            mPath.moveTo(x, y);
        //            mWhitePath.moveTo(x, y);
        //        } else {
        //            mPath.lineTo(x, y);
        //            mWhitePath.lineTo(x, halfHeight + (y - halfHeight) / 1.2f);
        //        }
        //    }
        //    drawBg(i % 2 == 0);
        //
        //    //mCanvas.restoreToCount(sc);
        //}
    }

    private void initSize() {
        if (height == 0 || width == 0) {
            height = getHeight();
            halfHeight = height / 2.0f;
            width = getWidth();
            halfWidth = width / 2.0f;
        }
    }

    private void resetCanvas() {
        mBgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCanvas.drawPaint(mBgPaint);
        mBgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        mCanvas.drawColor(Color.BLACK);
        mCanvas.drawLine(0, halfHeight, width, halfHeight, mWhitePaint);
    }

    public void drawBg(int color) {
        //if (b) line_1_start_color = color;
        int region_1_end_color = getResources().getColor(R.color.line_1_end_color);
        //int[] colors = new int[] { line_1_start_color, region_1_end_color, line_1_start_color };
        //float[] positions = new float[] { 0f, 0.9f, 1f };
        //LinearGradient gradient =
        //        new LinearGradient(100, halfHeight - 200, 100, halfHeight + 200, colors, positions,
        //                Shader.TileMode.REPEAT);
        //mPaint.setShader(gradient);
        mPath.close();
        mWhitePath.close();
        mPaint.setColor(color);
        mCanvas.drawPath(mPath, mPaint);
        //mCanvas.drawPath(mWhitePath, mTransWhitePaint);

        //mCanvas.drawPath(mPath, mTextPaint);
    }

    /**
     * 计算y轴位置
     *
     * @param tmpx x轴位置
     * @param type 方向
     * @param size 第几次绘制
     */
    private float getY(float tmpx, int type, float size) {
        //double sinFunc = Math.sin(midXX * Math.PI * tmpx - 0.5 * Math.PI);//这里的offset * Math.PI是偏移量φ
        //double recessionFunc = Math.pow(midXY / (4 + Math.pow(tmpx, 4)), 2.5);
        //return (float) (0.5 * sinFunc * recessionFunc * size * 8 * type + halfHeight);

        double sinFunc = Math.sin(0.75 * tmpx + 0.5 * Math.PI);//这里的offset * Math.PI是偏移量φ
        double recessionFunc = 20 / (20 + Math.pow(tmpx, 4));
        return (float) (sinFunc * recessionFunc * 8 * type) / size + halfHeight;
    }

    static int randomColor(int alpha) {
        Random rnd = new Random();
        alpha = Math.min(Math.max(1, alpha), 255);
        return Color.argb(alpha, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public ColorMatrix setContrast(float contrast) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[] {
                contrast, 0, 0, 0, 0, 0, contrast, 0, 0, 0, 0, 0, contrast, 0, 0, 0, 0, 0, 1, 0,
        });
        return colorMatrix;
    }
}
