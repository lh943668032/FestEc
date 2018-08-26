package com.lh.festec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.lh.core.app.Lh;
import com.lh.ec.database.DatabaseManager;
import com.lh.ec.icon.FontEcModule;

public class LhApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Lh.init(this)
                .withApiHost("http://www.wanandroid.com/tools/mockapi/8734/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    void initStetho(){
        Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
    }
}
