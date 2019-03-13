package com.baobeidaodao.springboot.quartz.service;

import com.baobeidaodao.springboot.quartz.model.JobModel;
import org.quartz.SchedulerException;

/**
 * @author litao
 */
public interface TaskService {

    void addJob(JobModel jobModel) throws SchedulerException;

    void scheduleJob(JobModel jobModel) throws SchedulerException;

    void deleteJob(JobModel jobModel) throws SchedulerException;

    void pauseJob(JobModel jobModel) throws SchedulerException;

    void rescheduleJob(JobModel jobModel) throws SchedulerException;

    void resumeJob(JobModel jobModel) throws SchedulerException;

    void triggerJob(JobModel jobModel) throws SchedulerException;

}
