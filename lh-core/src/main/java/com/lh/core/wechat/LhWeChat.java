package com.lh.core.wechat;

import android.app.Activity;

import com.lh.core.app.ConfigType;
import com.lh.core.app.Lh;
import com.lh.core.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author lh
 * @datetime 2018/9/4 23:11
 */
public class LhWeChat {

    public static final String APP_ID = Lh.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Lh.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public LhWeChat setSignInCallback(IWeChatSignInCallback signInCallback) {
        this.mSignInCallback = signInCallback;
        return this;
    }

    private static final class Holder{
        private static final LhWeChat INSTANCE = new LhWeChat();
    }

    public static LhWeChat getInstance(){
        return Holder.INSTANCE;
    }

    private LhWeChat(){
        final Activity activity = Lh.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI(){
        return WXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "123";
        WXAPI.sendReq(req);
    }
}
