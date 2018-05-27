package com.lh.core.net;

import android.provider.Telephony;
import android.view.WindowManager;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;
import com.lh.core.net.callback.RequestCallbacks;

import java.nio.file.FileAlreadyExistsException;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @quthor lh
 * @date 2018/5/27 20:10
 */
public class RestClient {

    private final String URL;
    private final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<ResponseBody> call = null;

        if(REQUEST != null){
            REQUEST.onRequestStart();
        }

        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
        }

        if(call != null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<ResponseBody> getRequestCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR);
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
