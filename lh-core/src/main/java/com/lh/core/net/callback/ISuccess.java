package com.lh.core.net.callback;

import okhttp3.ResponseBody;

/**
 * @quthor lh
 * @date 2018/5/27 20:13
 */
public interface ISuccess {

    void onSuccess(ResponseBody responseBody);

}
