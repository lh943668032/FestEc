package com.lh.festec;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.core.net.RestClient;
import com.lh.core.net.RestCreator;
import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;

import java.io.IOException;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "__lh__";
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        tv = findViewById(R.id.tv);
//        tv.setTypeface(iconfont);
        testClient();
        testRxjava();
    }

    //TODO: 测试方法
    void testRxjava(){

        String url = "";
        final WeakHashMap<String,Object> params = new WeakHashMap<>();
        final Observable<retrofit2.Response<ResponseBody>> observable = RestCreator.getRxRestService().get(url,params);
                observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<retrofit2.Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onNext(retrofit2.Response<ResponseBody> responseBodyResponse) {
                        Log.d(TAG, "onNext: code " + responseBodyResponse.code());
                        try {
                            Log.d(TAG, "onNext: " + responseBodyResponse.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void testClient() {
/*        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
            }
    });*/

        RestClient.builder()
                .url("")
                .loader(this)
//                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
//                        try {
//                            Toast.makeText(getApplicationContext(),responseBody.string(),Toast.LENGTH_SHORT).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }
                    @Override
                    public void onSuccess(String content) {

                    }
                })
                .failure(/*new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                }*/()->{
                    Log.d(TAG, "onFailure: ");
                })
                .error(/*new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d(TAG, "onError: ");
                    }
                }*/(code,msg)->{
                    Log.d(TAG, "onError: ");
                })
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                }).build().get();
    }
}
