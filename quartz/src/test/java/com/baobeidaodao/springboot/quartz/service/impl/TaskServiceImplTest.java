package com.baobeidaodao.springboot.quartz.service.impl;

import com.baobeidaodao.springboot.quartz.model.JobModel;
import com.baobeidaodao.springboot.quartz.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceImplTest {

    private JobModel jobModel = new JobModel();

    private Long id = 1L;
    private String group = "group";
    private String name = "name";
    private String description = "description";
    private String cron = "*/10 * * * * ?";

    @Resource
    private TaskService taskService;

    @Before
    public void setUp() throws Exception {
        jobModel.setId(id);
        jobModel.setGroup(group);
        jobModel.setName(name);
        jobModel.setDescription(description);
        jobModel.setCron(cron);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addJob() throws SchedulerException {
        taskService.addJob(jobModel);
    }

    @Test
    public void scheduleJob() throws SchedulerException {
        taskService.scheduleJob(jobModel);
    }

    @Test
    public void deleteJob() throws SchedulerException {
        taskService.deleteJob(jobModel);
    }

    @Test
    public void pauseJob() throws SchedulerException {
        taskService.pauseJob(jobModel);
    }

    @Test
    public void rescheduleJob() throws SchedulerException {
        taskService.rescheduleJob(jobModel);
    }

    @Test
    public void resumeJob() throws SchedulerException {
        taskService.resumeJob(jobModel);
    }

    @Test
    public void triggerJob() throws SchedulerException {
        taskService.triggerJob(jobModel);
    }

}