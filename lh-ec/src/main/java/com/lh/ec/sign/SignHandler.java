package com.lh.ec.sign;


import android.icu.util.IslamicCalendar;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lh.core.app.AccountManager;
import com.lh.ec.database.DatabaseManager;
import com.lh.ec.database.UserProfile;

/**
 * @author lh
 * @datetime 2018/6/21 23:07
 */

public class SignHandler {
    public static void onSignUp(String response, ISignListener iSignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        Log.d("USER_PROFILE", "onSignUp: " + userId);
        Log.d("USER_PROFILE", "onSignUp: " + name);
        Log.d("USER_PROFILE", "onSignUp: " + avatar);
        Log.d("USER_PROFILE", "onSignUp: " + gender);
        Log.d("USER_PROFILE", "onSignUp: " + address);
        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insertOrReplace(userProfile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener iSignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        Log.d("USER_PROFILE", "onSignUp: " + userId);
        Log.d("USER_PROFILE", "onSignUp: " + name);
        Log.d("USER_PROFILE", "onSignUp: " + avatar);
        Log.d("USER_PROFILE", "onSignUp: " + gender);
        Log.d("USER_PROFILE", "onSignUp: " + address);
        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insertOrReplace(userProfile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }
}
