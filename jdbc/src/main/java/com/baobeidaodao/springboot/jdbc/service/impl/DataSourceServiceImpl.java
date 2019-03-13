package com.baobeidaodao.springboot.jdbc.service.impl;

import com.baobeidaodao.springboot.jdbc.model.DataSourceModel;
import com.baobeidaodao.springboot.jdbc.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
@Slf4j
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public DataSource buildDataSource(Map<String, String> map) {
        String url = map.get("url");
        String username = map.get("username");
        String password = map.get("password");
        String driverClassName = map.get("driverClassName");
        String type = map.get("type");
        return buildDataSource(url, username, password, driverClassName, type);
    }

    @Override
    public DataSource buildDataSource(DataSourceModel dataSourceModel) {
        String url = dataSourceModel.getUrl();
        String username = dataSourceModel.getUsername();
        String password = dataSourceModel.getPassword();
        String driverClassName = dataSourceModel.getDriverClassName();
        String type = dataSourceModel.getType();
        return buildDataSource(url, username, password, driverClassName, type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(String url, String username, String password, String driverClassName, String type) {
        Class<? extends DataSource> dataSourceType = null;
        if (null != type) {
            try {
                dataSourceType = (Class<? extends DataSource>) Class.forName(type);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        } else {
            dataSourceType = DataSourceBuilder.findType(DataSourceBuilder.class.getClassLoader());
        }
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .type(dataSourceType)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Override
    public Boolean testDataSource(DataSource dataSource) {
        try {
            Connection connection = dataSource.getConnection();
            if (null != connection) {
                boolean valid = connection.isValid(5);
                connection.close();
                return valid;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> query(DataSource dataSource, String sql) {
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate.queryForList(sql);
    }

}
