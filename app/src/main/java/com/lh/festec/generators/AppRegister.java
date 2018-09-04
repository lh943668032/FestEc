package com.lh.festec.generators;

import com.lh.annotations.AppRegisterGenerator;
import com.lh.core.wechat.templete.AppRegisterTemplete;

/**
 * @author lh
 * @datetime 2018/8/27 0:15
 */
@AppRegisterGenerator(
        packageName = "com.lh.festec",
        registerTemplete = AppRegisterTemplete.class
)
public interface AppRegister {
}
