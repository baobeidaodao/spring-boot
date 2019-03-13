package com.baobeidaodao.springboot.quartz.service;

/**
 * @author DaoDao
 */
public interface JobService {

    Boolean testCronExpression(String cronExpression);

}
