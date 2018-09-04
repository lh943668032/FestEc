package com.lh.festec.generators;


import com.lh.annotations.EntryGenerator;
import com.lh.core.wechat.templete.WXEntryTemplete;

/**
 * @author lh
 * @datetime 2018/8/27 0:14
 */
@EntryGenerator(
        packageName = "com.lh.festec",
        entryTemplate = WXEntryTemplete.class
)
public interface WechatEntry {

}
