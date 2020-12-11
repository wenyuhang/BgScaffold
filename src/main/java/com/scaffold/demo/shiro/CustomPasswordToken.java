package com.scaffold.demo.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/7 18:42
 * desc   :
 */
public class CustomPasswordToken extends UsernamePasswordToken {
    private String token;

    public CustomPasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}