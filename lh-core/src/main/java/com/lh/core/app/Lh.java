package com.lh.core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

public final class Lh {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLhConfigs();
    }

}
