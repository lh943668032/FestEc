package com.lh.core.wechat.templete;

import com.lh.core.activities.ProxyActivity;
import com.lh.core.fragments.StandardFragment;
import com.lh.core.wechat.BaseWXActivity;
import com.lh.core.wechat.BaseWXEntryActivity;
import com.lh.core.wechat.LhWeChat;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;

/**
 * @author lh
 * @datetime 2018/8/27 0:11
 */
public class WXEntryTemplete extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LhWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
