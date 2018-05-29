package com.lh.core.net.callback;

import android.os.Handler;
import android.os.Looper;

import com.lh.core.ui.LhLoader;
import com.lh.core.ui.LoaderStyle;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @quthor lh
 * @date 2018/5/27 20:58
 */
public class RequestCallbacks implements Callback<ResponseBody> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        stopLoading();
    }

    private void stopLoading(){
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LhLoader.stopLoading();
                }
            }, 2000);
        }
    }
}
