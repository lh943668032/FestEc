package com.lh.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lh.core.fragments.StandardFragment;
import com.lh.core.net.RestClient;
import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.ISuccess;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ExampleFragment extends StandardFragment {
    private static final String TAG = "lh";
    @Override
    public Object setLayout() {
        return R.layout.fragment_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            String content = responseBody.string();
                            Log.d(TAG, "onSuccess1: " + content);
                            Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onSuccess(String content) {
                        Log.d(TAG, "onSuccess2: ");
                        Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {
                Log.d(TAG, "onFailure: ");
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                Log.d(TAG, "onError: ");
            }
        }).build().get();
    }
}
