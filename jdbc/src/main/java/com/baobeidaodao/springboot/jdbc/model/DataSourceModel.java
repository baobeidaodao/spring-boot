package com.baobeidaodao.springboot.jdbc.model;

import lombok.Data;

/**
 * @author DaoDao
 */
@Data
public class DataSourceModel {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String type;

}
