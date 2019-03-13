package com.baobeidaodao.springboot.quartz.service.impl;

import com.baobeidaodao.springboot.quartz.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceImplTest {

    @Resource
    private JobService jobService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCronExpression() {
        String cronExpression = "*/10 * * * * ?";
        Boolean b = jobService.testCronExpression(cronExpression);
    }

}