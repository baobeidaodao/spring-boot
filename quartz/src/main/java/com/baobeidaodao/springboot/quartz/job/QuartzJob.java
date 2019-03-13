package com.baobeidaodao.springboot.quartz.job;

import com.baobeidaodao.springboot.quartz.model.JobModel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author DaoDao
 */
@Slf4j
@Component
public class QuartzJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        long id = jobDataMap.getLong("id");
        log.info("execute id: " + id);
        /*
         * 此处反序列化失败，不知道为什么
         * 可以尝试使用自定义的序列化方案，比如 json
         * 或者应用了其他持久化方案，jobDataMap 中可以只存放 id，根据 id 去查询出其他任务参数
         */
        JobModel jobModel = (JobModel) jobDataMap.get("jobModel");
        log.info(jobModel.toString());
    }

}
