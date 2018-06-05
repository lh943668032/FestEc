package com.lh.festec;

import android.util.Log;
import android.widget.TextView;

import com.lh.core.activities.ProxyActivity;
import com.lh.core.fragments.StandardFragment;
import com.lh.core.net.RestClient;
import com.lh.core.net.RestCreator;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;
import com.lh.ec.launcher.LauncherFragment;

import java.io.IOException;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends ProxyActivity {

    private static final String TAG = "__lh__";
    public TextView tv;

    @Override
    public StandardFragment setRootFragment() {
        return new LauncherFragment();
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
