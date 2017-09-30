package com.haohaohu.androidsample;

/**
 * @author haohao(ronghao3508@gmail.com) on 2017/9/29 下午 01:40
 * @version v1.0
 */
public class Point {
    public int y;
    public float size = 0.5f;
    public int xoff;
    public Point pre;
    public Point next;

    public int color;

    public Point(int y, int xoff, int color, Point pre, Point next) {
        this.y = y;
        this.color = color;
        this.xoff = xoff;
        this.pre = pre;
        this.next = next;
    }

    public float getYY() {
        size += 0.04f;

        if (size >= 1) {
            if (pre == null) {
                return Integer.MAX_VALUE;
            } else {
                pre.next = next;
                return 0f;
            }
        } else {
            float aa = size - 0.6f;
            if (Math.abs(aa) <= 0.08f) {
                aa = 0.08f;
            } else {
                aa = Math.abs(aa);
            }
            return aa*8;
        }
    }
}
