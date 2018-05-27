package com.lh.core.net;

import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * @quthor lh
 * @date 2018/5/27 20:10
 */
public class RestClientBuilder {

    private String mUrl;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private RequestBody mBody;

    RestClientBuilder(){

    }

    public final RestClientBuilder  url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder  params(Map<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder  params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder  raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json,charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder  success(ISuccess iSuccess){
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder  failure(IFailure iFailure){
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder  error(IError iError){
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder  request(IRequest iRequest){
        this.mRequest = iRequest;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mRequest,mSuccess,mFailure,mError,mBody);
    }

}
