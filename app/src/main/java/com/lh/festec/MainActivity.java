package com.lh.festec;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        TextView tv = findViewById(R.id.tv);
        tv.setTypeface(iconfont);
    }
}
