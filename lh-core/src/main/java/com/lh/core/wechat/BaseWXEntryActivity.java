package com.lh.core.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lh.core.net.RestClient;
import com.lh.core.net.callback.IError;
import com.lh.core.net.callback.IFailure;
import com.lh.core.net.callback.ISuccess;
import com.lh.core.util.log.LhLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import okhttp3.ResponseBody;

/**
 * @author lh
 * @datetime 2018/9/4 23:41
 */
public abstract class BaseWXEntryActivity extends BaseWXActivity {

    //用户登录成功后回调
    protected abstract void onSignInSuccess(String userInfo);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LhWeChat.APP_ID)
                .append("&secret=")
                .append(LhWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        LhLogger.d("authUrl", authUrl.toString());
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {
        RestClient.builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {

                    }

                    @Override
                    public void onSuccess(String content) {
                        final JSONObject jsonObject = JSON.parseObject(content);
                        final String accessToken = jsonObject.getString("access_token");
                        final String openId = jsonObject.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang")
                                .append("zh_CN");
                        LhLogger.d("userInfoUrl", userInfoUrl.toString());

                    }
                }).build()
                .get();
    }

    private void getUserInfo(String userInfoUrl) {
        RestClient.builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {

                    }

                    @Override
                    public void onSuccess(String content) {
                        onSignInSuccess(content);
                    }
                }).
                failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                }).error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                }).build().get();

    }
}