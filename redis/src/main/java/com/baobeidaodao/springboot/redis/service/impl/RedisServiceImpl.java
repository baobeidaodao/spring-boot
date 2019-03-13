package com.baobeidaodao.springboot.redis.service.impl;

import com.baobeidaodao.springboot.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DaoDao
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Object getValue(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

}
