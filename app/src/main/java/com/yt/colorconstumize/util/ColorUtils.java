package com.yt.colorconstumize.util;

import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.yt.colorconstumize.color.ColorKey;

public class ColorUtils {

    public static int getColor(SharedPreferences sp, ColorKey colorKey) {
        return Color.rgb(
                sp.getInt(colorKey + ".r", 0),
                sp.getInt(colorKey + ".g", 0),
                sp.getInt(colorKey + ".b", 0)
        );
    }

    public static int[] getRgb(SharedPreferences sp, ColorKey colorKey) {
        return new int[]{
                sp.getInt(colorKey + ".r", 0),
                sp.getInt(colorKey + ".g", 0),
                sp.getInt(colorKey + ".b", 0)
        };
    }

    public static void firstRun(SharedPreferences sp) {
        if (sp.getAll().isEmpty()) {
            SharedPreferences.Editor editor = sp.edit();
            for (ColorKey colorKey : ColorKey.values()) {
                editor.putInt(colorKey + ".r", colorKey.getR());
                editor.putInt(colorKey + ".g", colorKey.getG());
                editor.putInt(colorKey + ".b", colorKey.getB());
            }
            editor.apply();
        }
    }
}
