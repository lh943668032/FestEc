package com.lh.festec;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.core.activities.ProxyActivity;
import com.lh.core.fragments.StandardFragment;
import com.lh.core.net.RestClient;
import com.lh.core.net.RestCreator;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;
import com.lh.core.ui.launcher.ILauncherListener;
import com.lh.core.ui.launcher.OnLauncherFinishTag;
import com.lh.ec.launcher.LauncherFragment;
import com.lh.ec.launcher.LauncherScrollFragment;
import com.lh.ec.sign.ISignListener;
import com.lh.ec.sign.SignInFragment;
import com.lh.ec.sign.SignUpFragment;

import java.io.IOException;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    private static final String TAG = "__lh__";
    public TextView tv;

    @Override
    public StandardFragment setRootFragment() {
        return new LauncherFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    //TODO
    void test3() {
        Intent intent = new Intent();
        startActivity(intent);
    }

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_main);
    ////        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
    //        tv = findViewById(R.id.tv);
    ////        tv.setTypeface(iconfont);
    //        testClient();
    //        testRxjava();
    //    }
    //
    //    //TODO: 测试方法
    void testRxjava() {

        String url = "";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();
        final Observable<retrofit2.Response<ResponseBody>> observable = RestCreator.getRxRestService().get(url, params);
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
                }*/() -> {
                    Log.d(TAG, "onFailure: ");
                })
                .error(/*new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d(TAG, "onError: ");
                    }
                }*/(code, msg) -> {
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

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this,"启动结束，用户登录了",Toast.LENGTH_SHORT).show();
                startWithPop(new ExampleFragment());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"启动结束，用户没登录",Toast.LENGTH_SHORT).show();
                startWithPop(new SignInFragment());
                break;
            default:
                break;
        }
    }
}
