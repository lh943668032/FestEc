package com.lh.festec;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.core.net.RestClient;
import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends Activity {

    int count = 5;
    public TextView tv;
    public Animation animation;
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.startAnimation(animation);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        tv = findViewById(R.id.tv);
        tv.setText(count+" s");
//        tv.setTypeface(iconfont);
//        testClient();
        animation = AnimationUtils.loadAnimation(this,R.anim.scale_anims);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("lh", "onAnimationEnd: " + count);
                if(--count > 0) {
                    tv.setText(count + " s");
                    mHandler.sendEmptyMessage(0);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tv.startAnimation(animation);
    }

    private void testClient(){
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
//                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            Toast.makeText(getApplicationContext(),responseBody.string(),Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
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
