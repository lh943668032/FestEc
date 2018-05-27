package com.lh.core.net;

import android.content.Context;

import com.lh.core.app.ConfigType;
import com.lh.core.app.Lh;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RestCreator {

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    private static final class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Lh.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OKHTTP_CLIENT)
//                .addConverterFactory(Rx)
                .build();
    }

    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OKHTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

}
