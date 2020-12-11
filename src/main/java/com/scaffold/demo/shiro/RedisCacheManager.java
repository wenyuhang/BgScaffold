package com.scaffold.demo.shiro;

import com.scaffold.demo.common.service.RedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/7 18:31
 * desc   :
 */
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisService redisService;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache(name, redisService);
    }
}