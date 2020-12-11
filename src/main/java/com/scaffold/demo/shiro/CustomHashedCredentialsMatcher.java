package com.scaffold.demo.shiro;

import com.scaffold.demo.common.service.RedisService;
import com.scaffold.demo.constant.Constant;
import com.scaffold.demo.enums.BaseResponseCode;
import com.scaffold.demo.exceptions.BusinessException;
import com.scaffold.demo.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/7 18:14
 * desc   :
 */
public class CustomHashedCredentialsMatcher extends SimpleCredentialsMatcher {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomPasswordToken customPasswordToken= (CustomPasswordToken) token;
        String accessToken = (String) customPasswordToken.getPrincipal();
        String userId= JwtTokenUtil.getUserId(accessToken);
        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK_ERROR);
        }
        if(redisService.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(redisService.hasKey(Constant.JWT_REFRESH_TOKEN_BLACKLIST+accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        if(redisService.hasKey(Constant.JWT_REFRESH_STATUS+accessToken)){
            return true;
        }
        if(JwtTokenUtil.isTokenExpired(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)&&redisService.getExpire(Constant.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
            if(!redisService.hasKey(Constant.JWT_REFRESH_IDENTIFICATION+accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }
        return true;
    }
}
