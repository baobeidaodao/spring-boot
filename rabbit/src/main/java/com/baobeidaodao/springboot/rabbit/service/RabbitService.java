package com.baobeidaodao.springboot.rabbit.service;

public interface RabbitService {

    void convertAndSend(String routingKey, Object message);

}
