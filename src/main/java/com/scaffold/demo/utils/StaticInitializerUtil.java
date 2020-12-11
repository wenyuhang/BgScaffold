package com.scaffold.demo.utils;

import org.springframework.stereotype.Component;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 12:19
 * desc   :
 */
@Component
public class StaticInitializerUtil {

    private TokenSettings tokenSettings;

    public StaticInitializerUtil(TokenSettings tokenSettings) {
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}
