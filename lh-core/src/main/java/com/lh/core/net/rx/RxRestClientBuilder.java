package com.lh.core.net.rx;

import android.content.Context;

import com.lh.core.net.RestClient;
import com.lh.core.net.RestCreator;
import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;
import com.lh.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @quthor lh
 * @date 2018/5/27 20:10
 */
public class RxRestClientBuilder {

    private String mUrl = null;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    RxRestClientBuilder(){

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(Map<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }


    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String filePath){
        this.mFile = new File(filePath);
        return this;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle){
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(mUrl,PARAMS,mBody,mFile,mContext,mLoaderStyle);
    }

}
