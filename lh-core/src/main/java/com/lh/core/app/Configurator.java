package com.lh.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import static com.lh.core.app.ConfigType.CONFIG_READY;
import static com.lh.core.app.ConfigType.ICON;

public class Configurator {
    private static final HashMap<String,Object> LH_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator(){
        LH_CONFIGS.put(CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String,Object> getLhConfigs(){
        return LH_CONFIGS;
    }

    private void initIcons(){
        if(ICONS.size() > 0){
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i = 1;i < ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final Configurator withIcon(IconFontDescriptor iconFontDescriptor){
        ICONS.add(iconFontDescriptor);
        return this;
    }

    public final void configure(){
        initIcons();
        LH_CONFIGS.put(CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String hostName){
        LH_CONFIGS.put(ConfigType.API_HOST.name(),hostName);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LH_CONFIGS.get(ConfigType.CONFIG_READY);
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LH_CONFIGS.get(key.name());
    }
}
