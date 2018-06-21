package com.lh.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.lh.core.fragments.StandardFragment;
import com.lh.core.net.RestClient;
import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.IRequest;
import com.lh.core.net.callback.ISuccess;
import com.lh.core.util.log.LhLogger;
import com.lh.ec.R;
import com.lh.ec.R2;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * @author lh
 * @datetime 2018/6/7 23:56
 */

public class SignUpFragment extends StandardFragment {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if(checkForm()){
            RestClient.builder()
                    .url("register")
                    .params("","")
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
                            Log.d("USER_PROFILE", "onError: ");
                        }
                    })
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(ResponseBody responseBody) {
                            Log.d("USER_PROFILE", "onSuccess: ");
                            try {
                                String content = responseBody.string();
                                Log.d("USER_PROFILE", "onSuccess: "+content);
                                LhLogger.json("USER_PROFILE",content);
                            } catch (IOException e) {
                                Log.d("USER_PROFILE",e.toString());
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onSuccess(String content) {
                            LhLogger.json("USER_PROFILE",content);
                        }
                    }).build().post();
            Toast.makeText(getContext(),"验证通过",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink(){
        start(new SignInFragment());
    }

    private boolean checkForm(){
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if(name.isEmpty()){
            mName.setError("请输入姓名");
            isPass = false;
        }else{
            mName.setError(null);
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else{
            mEmail.setError(null);
        }

        if(phone.isEmpty() || phone.length() != 11){
            mPhone.setError("手机号码错误");
            isPass = false;
        }else{
            mPhone.setError(null);
        }

        if(password.isEmpty() || password.length() < 6){
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        }else{
            mPassword.setError(null);
        }

        if(rePassword.isEmpty() || rePassword.length() < 6||!rePassword.equals(password)){
            mRePassword.setError("密码验证错误");
            isPass = false;
        }else{
            mRePassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
