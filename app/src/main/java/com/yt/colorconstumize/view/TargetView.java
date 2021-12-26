package com.yt.colorconstumize.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.yt.colorconstumize.color.ColorKey;
import com.yt.colorconstumize.util.ColorUtils;


/**
 * 靶面
 */
public class TargetView extends View {

    private Paint targetPaint;
    /**
     * 0: pistol
     * 1: rifle
     */
    private int mode = 0;

    private int color1;
    private int color2;

    private SharedPreferences sp;

    public TargetView(Context context) {
        super(context);
        init();
    }

    public TargetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TargetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 设置手枪靶或步枪靶
     *
     * @param mode PISTOL_TYPE或者RIFLE_TYPE
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * 此方法为具体绘制过程，若无特殊情况不需要进行改动
     *
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (mode == 0) {
            int targetCenter = getHeight() / 2;
            double size = getHeight() * 0.95 / 155.5;
            double baseRadius = 5.75;
            targetPaint.setAntiAlias(true);
            targetPaint.setColor(color1);
            targetPaint.setTextSize((int) ((baseRadius - 1) * size));
            targetPaint.setTextAlign(Paint.Align.CENTER);
            targetPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(targetCenter, targetCenter, (int) ((baseRadius + 8 * 3) * size), targetPaint);
            targetPaint.setColor(color2);

            for (int i = 0; i < 10; i++) {
                int radius = (int) ((baseRadius + 8 * i) * size);
                if (i >= 2) {
                    targetPaint.setStyle(Paint.Style.FILL);
                    canvas.drawText(String.valueOf(10 - i), targetCenter, (int) (targetCenter + radius - 2 * size), targetPaint);
                    canvas.drawText(String.valueOf(10 - i), targetCenter, (int) (targetCenter - radius + 6 * size), targetPaint);
                    canvas.drawText(String.valueOf(10 - i), (int) (targetCenter + radius - 4.0 * size), targetCenter + (int) ((baseRadius - 4.25) * size), targetPaint);
                    canvas.drawText(String.valueOf(10 - i), (int) (targetCenter - radius + 4.0 * size), targetCenter + (int) ((baseRadius - 4.25) * size), targetPaint);
                    if (i > 2)
                        targetPaint.setColor(color1);
                }
                targetPaint.setStrokeWidth((int) (baseRadius / 2));
                targetPaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(targetCenter, targetCenter, radius, targetPaint);
            }
            targetPaint.setColor(color2);
            canvas.drawCircle(targetCenter, targetCenter, (int) ((baseRadius - 3.25) * size), targetPaint);
        } else {
            int targetCenter = getHeight() / 2;
            double size = getHeight() * 0.95 / 455;
            double baseRadius = 5 / 2.0;

            targetPaint.setAntiAlias(true);
            targetPaint.setColor(color1);
            targetPaint.setTextSize((int) (baseRadius * 6 * size));
            targetPaint.setStrokeWidth(3);
            targetPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawCircle(targetCenter, targetCenter, (int) ((baseRadius + baseRadius * 10 * 6) * size), targetPaint);
            targetPaint.setColor(color2);

            for (int i = 0; i < 10; i++) {
                targetPaint.setStyle(Paint.Style.STROKE);
                int radius = (int) ((baseRadius + baseRadius * 10 * i) * size);
                if (i == 0) targetPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(targetCenter, targetCenter, radius, targetPaint);
                if (i >= 2) {
                    targetPaint.setStyle(Paint.Style.FILL);
                    canvas.drawText(String.valueOf(10 - i), targetCenter, (int) (targetCenter + radius - 12 * size / 2), targetPaint);
                    canvas.drawText(String.valueOf(10 - i), targetCenter, (int) (targetCenter - radius + 38 * size / 2), targetPaint);
                    canvas.drawText(String.valueOf(10 - i), (int) (targetCenter + radius - 25 * size / 2), targetCenter + (int) ((baseRadius * 2.5) * size / 2), targetPaint);
                    canvas.drawText(String.valueOf(10 - i), (int) (targetCenter - radius + 25 * size / 2), targetCenter + (int) ((baseRadius * 2.5) * size / 2), targetPaint);
                    if (i > 5)
                        targetPaint.setColor(color1);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    private void init() {
        sp = getContext().getSharedPreferences("colors", Context.MODE_PRIVATE);
        targetPaint = new Paint();
        System.out.println(color1+"-----------------");
        loadColor();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        loadColor();
    }

    private void loadColor(){
        color1 = ColorUtils.getColor(sp, ColorKey.TARGET_VIEW_1);
        color2 = ColorUtils.getColor(sp, ColorKey.TARGET_VIEW_2);
    }
}
