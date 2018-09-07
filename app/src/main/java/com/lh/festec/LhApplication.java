package com.lh.festec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.lh.annotations.EntryGenerator;
import com.lh.core.app.Lh;
import com.lh.core.net.interceptor.DebugInterceptor;
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
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withWeChatAppId("wx0bb079cc5b32ba81")
                .withWeChatAppSecret("wx0bb079cc5b32ba81")
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
