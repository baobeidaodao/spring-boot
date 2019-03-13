package com.baobeidaodao.springboot.quartz.runner;

import com.baobeidaodao.springboot.quartz.model.JobModel;
import com.baobeidaodao.springboot.quartz.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DaoDao
 */
@Slf4j
@Component
@Order(value = 2)
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Resource
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner running");

        JobModel jobModel = new JobModel();

        Long id = 1L;
        String group = "group";
        String name = "name";
        String description = "description";
        String cron = "*/3 * * * * ?";

        jobModel.setId(id);
        jobModel.setGroup(group);
        jobModel.setName(name);
        jobModel.setDescription(description);
        jobModel.setCron(cron);

        // taskService.addJob(jobModel);
        // taskService.scheduleJob(jobModel);
        // taskService.deleteJob(jobModel);
        // taskService.pauseJob(jobModel);
        // taskService.rescheduleJob(jobModel);
        // taskService.resumeJob(jobModel);
        // taskService.triggerJob(jobModel);
    }

}
