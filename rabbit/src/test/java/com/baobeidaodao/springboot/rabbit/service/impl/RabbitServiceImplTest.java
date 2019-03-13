package com.baobeidaodao.springboot.rabbit.service.impl;

import com.baobeidaodao.springboot.rabbit.service.RabbitService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitServiceImplTest {

    @Resource
    private RabbitService rabbitService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void convertAndSend() {
        String routingKey = "aaa";
        String message = "bbb";
        rabbitService.convertAndSend(routingKey, message);
    }
}