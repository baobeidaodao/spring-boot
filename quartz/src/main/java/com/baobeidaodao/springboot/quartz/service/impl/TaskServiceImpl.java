package com.baobeidaodao.springboot.quartz.service.impl;

import com.baobeidaodao.springboot.quartz.job.QuartzJob;
import com.baobeidaodao.springboot.quartz.model.JobModel;
import com.baobeidaodao.springboot.quartz.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author litao
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Qualifier("schedulerFactoryBean")
    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(JobModel jobModel) throws SchedulerException {
        JobDetail jobDetail = buildJobDetail(jobModel);
        JobKey jobKey = jobDetail.getKey();
        boolean exists = scheduler.checkExists(jobKey);
        if (!exists) {
            scheduler.addJob(jobDetail, false);
        }
    }

    @Override
    public void scheduleJob(JobModel jobModel) throws SchedulerException {
        JobDetail jobDetail = buildJobDetail(jobModel);
        CronTrigger cronTrigger = buildCronTrigger(jobModel);
        JobKey jobKey = jobDetail.getKey();
        boolean exists = scheduler.checkExists(jobKey);
        if (!exists) {
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } else {
            scheduler.resumeJob(jobKey);
        }
    }

    @Override
    public void deleteJob(JobModel jobModel) throws SchedulerException {
        JobKey jobKey = buildJobKey(jobModel);
        boolean exists = scheduler.checkExists(jobKey);
        if (exists) {
            scheduler.deleteJob(jobKey);
        }
    }

    @Override
    public void pauseJob(JobModel jobModel) throws SchedulerException {
        JobKey jobKey = buildJobKey(jobModel);
        boolean exists = scheduler.checkExists(jobKey);
        if (exists) {
            TriggerKey triggerKey = buildTriggerKey(jobModel);
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            if (Trigger.TriggerState.NORMAL == triggerState) {
                scheduler.pauseJob(jobKey);
            }
        }
    }

    @Override
    public void rescheduleJob(JobModel jobModel) throws SchedulerException {
        CronTrigger cronTrigger = buildCronTrigger(jobModel);
        JobDetail jobDetail = buildJobDetail(jobModel);
        JobKey jobKey = jobDetail.getKey();
        boolean exists = scheduler.checkExists(jobKey);
        if (!exists) {
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } else {
            TriggerKey triggerKey = buildTriggerKey(jobModel);
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        }
    }

    @Override
    public void resumeJob(JobModel jobModel) throws SchedulerException {
        JobKey jobKey = buildJobKey(jobModel);
        boolean exists = scheduler.checkExists(jobKey);
        if (!exists) {
            JobDetail jobDetail = buildJobDetail(jobModel);
            scheduler.addJob(jobDetail, false);
            CronTrigger cronTrigger = buildCronTrigger(jobModel);
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } else {
            scheduler.resumeJob(jobKey);
        }
    }

    @Override
    public void triggerJob(JobModel jobModel) throws SchedulerException {
        JobKey jobKey = buildJobKey(jobModel);
        boolean exists = scheduler.checkExists(jobKey);
        if (exists) {
            scheduler.triggerJob(jobKey);
        }
    }

    private JobKey buildJobKey(JobModel jobModel) {
        String name = jobModel.getName();
        String group = jobModel.getGroup();
        return JobKey.jobKey(name, group);
    }

    private TriggerKey buildTriggerKey(JobModel jobModel) {
        String name = jobModel.getName();
        String group = jobModel.getGroup();
        return TriggerKey.triggerKey(name, group);
    }

    private JobDetail buildJobDetail(JobModel jobModel) {
        String jobDescription = jobModel.getDescription();
        JobKey jobKey = buildJobKey(jobModel);
        JobDataMap jobDataMap = buildJobDataMap(jobModel);
        return JobBuilder
                .newJob(QuartzJob.class)
                .withIdentity(jobKey)
                .withDescription(jobDescription)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private JobDataMap buildJobDataMap(JobModel jobModel) {
        JobDataMap jobDataMap = new JobDataMap();
        Long id = jobModel.getId();
        jobDataMap.put("id", id);
        jobDataMap.put("jobModel", jobModel);
        return jobDataMap;
    }

    private CronTrigger buildCronTrigger(JobModel jobModel) {
        String cronExpression = jobModel.getCron();
        String jobDescription = jobModel.getDescription();
        TriggerKey triggerKey = buildTriggerKey(jobModel);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                .withMisfireHandlingInstructionDoNothing();
        return TriggerBuilder.newTrigger().withIdentity(triggerKey)
                .withDescription(jobDescription)
                .withSchedule(scheduleBuilder)
                .build();
    }

}
