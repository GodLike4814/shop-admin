package com.fh.redis.impl;

import com.fh.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements IRedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setStringKeyAndValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Boolean isExistKey(String categoryAll) {
        return redisTemplate.hasKey(categoryAll);
    }

    @Override
    public Object getStringValueByKey(String categoryAll) {
        return redisTemplate.opsForValue().get(categoryAll);
    }

}
