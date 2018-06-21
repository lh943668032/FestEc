package com.lh.festec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.lh.core.app.Lh;
import com.lh.ec.database.DatabaseManager;
import com.lh.ec.icon.FontEcModule;

public class LhApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Lh.init(this)
                .withApiHost("http://10.0.2.2:5000/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
