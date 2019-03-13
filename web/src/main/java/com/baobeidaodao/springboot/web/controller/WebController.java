package com.baobeidaodao.springboot.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DaoDao
 * @date 2019-02-13 11:53
 */
@Slf4j
@RestController
@RequestMapping(value = "")
public class WebController {

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping(value = "")
    public String web() {
        log.info(applicationName);
        return applicationName;
    }
}
