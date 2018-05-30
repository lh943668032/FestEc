package com.lh.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.lh.core.app.Lh;

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Lh.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Lh.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
