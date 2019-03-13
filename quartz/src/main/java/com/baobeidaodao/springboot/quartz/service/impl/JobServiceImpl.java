package com.baobeidaodao.springboot.quartz.service.impl;

import com.baobeidaodao.springboot.quartz.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

/**
 * @author DaoDao
 */
@Slf4j
@Service
public class JobServiceImpl implements JobService {
    @Override
    public Boolean testCronExpression(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }
}
