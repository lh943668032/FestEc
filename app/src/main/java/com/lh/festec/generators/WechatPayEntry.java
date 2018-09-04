package com.lh.festec.generators;

import com.lh.annotations.EntryGenerator;
import com.lh.annotations.PayEntryGenerator;
import com.lh.core.wechat.templete.WXEntryTemplete;
import com.lh.core.wechat.templete.WXPayEntryTemplete;

/**
 * @author lh
 * @datetime 2018/8/27 0:14
 */
@PayEntryGenerator(
        packageName = "com.lh.festec",
        payEntryTemplete = WXPayEntryTemplete.class
)
public interface WechatPayEntry {
}
