package com.baobeidaodao.springboot.rabbit.service.impl;

import com.baobeidaodao.springboot.rabbit.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DaoDao
 */
@Slf4j
@Service
public class RabbitServiceImpl implements RabbitService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void convertAndSend(String routingKey, Object message) {
        rabbitTemplate.convertAndSend(routingKey, message);
    }

}
