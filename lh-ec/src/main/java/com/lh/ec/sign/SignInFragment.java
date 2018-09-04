package com.lh.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.lh.core.fragments.StandardFragment;
import com.lh.core.net.RestClient;
import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;
import com.lh.core.util.log.LhLogger;
import com.lh.core.wechat.LhWeChat;
import com.lh.core.wechat.callbacks.IWeChatSignInCallback;
import com.lh.ec.R;
import com.lh.ec.R2;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * @author lh
 * @datetime 2018/6/10 22:13
 */

public class SignInFragment extends StandardFragment {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    public ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if(checkForm()){
            RestClient.builder()
                    .url("login")
                    .params("email",mEmail.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .request(new IRequest() {
                        @Override
                        public void onRequestStart() {
                            Log.d("USER_PROFILE", "onRequestStart: ");
                        }

                        @Override
                        public void onRequestEnd() {
                            Log.d("USER_PROFILE", "onRequestEnd: ");
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Log.d("USER_PROFILE", "onFailure: ");
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Log.d("USER_PROFILE", "onError: " + code);
                            Log.d("USER_PROFILE", "onError: " + msg);
                        }
                    })
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(ResponseBody responseBody) {
                            Log.d("USER_PROFILE", "onSuccess: ");
                            try {
                                String content = responseBody.string();
                                Log.d("USER_PROFILE", "onSuccess: "+content);
//                                LhLogger.json("USER_PROFILE",content);
                                SignHandler.onSignIn(content,mISignListener);
                            } catch (IOException e) {
                                Log.d("USER_PROFILE",e.toString());
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onSuccess(String content) {
                            LhLogger.json("USER_PROFILE",content);
                        }
                    }).build().get();
        }
    }

    @OnClick(R2.id.icon_sign_in_we_chat)
    void onClickWeChat(){
        LhWeChat.getInstance().setSignInCallback(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).signIn();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpFragment());
    }

    private boolean checkForm(){
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;


        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else{
            mEmail.setError(null);
        }

        if(password.isEmpty() || password.length() < 6){
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        }else{
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
