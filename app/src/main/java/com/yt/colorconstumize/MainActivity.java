package com.yt.colorconstumize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import static com.yt.colorconstumize.color.ColorKey.*;

import com.yt.colorconstumize.color.ColorKey;
import com.yt.colorconstumize.util.ColorUtils;
import com.yt.colorconstumize.view.TargetView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TargetView targetView;
    private SeekBar seekBarR;
    private SeekBar seekBarG;
    private SeekBar seekBarB;

    private Button btn_1;
    private Button btn_2;

    private SharedPreferences sp;

    private ColorKey colorKey = TARGET_VIEW_1;

    private View currentEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("colors", MODE_PRIVATE);
        ColorUtils.firstRun(sp);
        setContentView(R.layout.activity_main);

        targetView = findViewById(R.id.tv_target_view);
        seekBarR = findViewById(R.id.sb_r);
        seekBarG = findViewById(R.id.sb_g);
        seekBarB = findViewById(R.id.sb_b);

        btn_1 = findViewById(R.id.btn_target_1);
        btn_2 = findViewById(R.id.btn_target_2);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);

        currentEditView = targetView;
        int[] rgb = ColorUtils.getRgb(sp, colorKey);
        seekBarR.setProgress(rgb[0]);
        seekBarG.setProgress(rgb[1]);
        seekBarB.setProgress(rgb[2]);

        seekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sp.edit().putInt(colorKey + ".r", progress).apply();
                currentEditView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sp.edit().putInt(colorKey + ".g", progress).apply();
                currentEditView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sp.edit().putInt(colorKey + ".b", progress).apply();
                currentEditView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_target_1) {
            currentEditView = targetView;
            colorKey = TARGET_VIEW_1;
        }
        if (v.getId() == R.id.btn_target_2) {
            colorKey = TARGET_VIEW_2;
        }
        int[] rgb = ColorUtils.getRgb(sp, colorKey);
        seekBarR.setProgress(rgb[0]);
        seekBarG.setProgress(rgb[1]);
        seekBarB.setProgress(rgb[2]);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    /**
     * 设置 Activity 全屏
     */
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}