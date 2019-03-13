package com.baobeidaodao.springboot.redis.service.impl;

import com.baobeidaodao.springboot.redis.service.RedisService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceImplTest {

    @Resource
    private RedisService redisService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getValue() {
        Object key = "aaa";
        Object value = redisService.getValue(key);
    }

}