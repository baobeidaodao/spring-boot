package com.baobeidaodao.springboot.quartz.config;

import com.baobeidaodao.springboot.quartz.component.JobFactoryComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @author litao
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private JobFactoryComponent jobFactoryComponent;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("quartzDataSource") DataSource dataSource) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 设置数据源(使用系统的主数据源，覆盖properties文件的dataSource配置)
        schedulerFactoryBean.setDataSource(dataSource);
        //设置自定义Job Factory，用于Spring管理Job bean
        schedulerFactoryBean.setJobFactory(jobFactoryComponent);
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        return schedulerFactoryBean;
    }

}
