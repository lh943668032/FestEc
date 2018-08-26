package com.lh.core.app;

import com.lh.core.util.storage.LhPreference;

/**
 * @author lh
 * @datetime 2018/7/28 23:29
 */
public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state){
        LhPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignUp(){
        return LhPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker userChecker){
        if(isSignUp()){
            userChecker.onSignIn();
       }else{
            userChecker.onNotSignIn();
        }
    }

}
