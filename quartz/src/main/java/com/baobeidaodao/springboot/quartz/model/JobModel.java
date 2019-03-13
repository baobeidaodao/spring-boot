package com.baobeidaodao.springboot.quartz.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DaoDao
 */
@Data
public class JobModel implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String group;
    private String description;
    private String cron;
}
