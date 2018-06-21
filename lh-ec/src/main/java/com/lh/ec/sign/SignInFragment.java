package com.lh.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.lh.core.fragments.StandardFragment;
import com.lh.ec.R;
import com.lh.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lh
 * @datetime 2018/6/10 22:13
 */

public class SignInFragment extends StandardFragment {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){

    }

    @OnClick(R2.id.icon_sign_in_we_chat)
    void onClickWeChat(){

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
