package com.baobeidaodao.springboot.jdbc.service.impl;

import com.baobeidaodao.springboot.jdbc.model.DataSourceModel;
import com.baobeidaodao.springboot.jdbc.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceServiceImplTest {

    @Resource
    private DataSourceService dataSourceService;

    private DataSourceModel dataSourceModel = new DataSourceModel();

    private Map<String, String> map = new HashMap<>();

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String type;

    private String sql;

    @Before
    public void setUp() throws Exception {
        url = "jdbc:mysql://39.96.182.218:3306/daodao?useSSL=false&autoReconnect=true&characterEncoding=utf-8";
        username = "root";
        password = "root";
        driverClassName = "com.mysql.cj.jdbc.Driver";
        type = "com.mysql.cj.jdbc.MysqlDataSource";

        dataSourceModel.setUrl(url);
        dataSourceModel.setUsername(username);
        dataSourceModel.setPassword(password);
        dataSourceModel.setDriverClassName(driverClassName);
        dataSourceModel.setType(type);

        map.put("url", url);
        map.put("username", username);
        map.put("password", password);
        map.put("driverClassName", driverClassName);
        map.put("type", type);

        sql = "select * from test";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void buildDataSource() {
        DataSource dataSource = dataSourceService.buildDataSource(map);
    }

    @Test
    public void buildDataSource1() {
        DataSource dataSource = dataSourceService.buildDataSource(dataSourceModel);
    }

    @Test
    public void buildDataSource2() {
        DataSource dataSource = dataSourceService.buildDataSource(url, username, password, driverClassName, type);
    }

    @Test
    public void testDataSource() {
        DataSource dataSource = dataSourceService.buildDataSource(url, username, password, driverClassName, type);
        Boolean b = dataSourceService.testDataSource(dataSource);
    }

    @Test
    public void query() {
        DataSource dataSource = dataSourceService.buildDataSource(url, username, password, driverClassName, type);
        List<Map<String, Object>> list = dataSourceService.query(dataSource, sql);
    }

}