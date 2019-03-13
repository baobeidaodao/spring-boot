package com.baobeidaodao.springboot.jdbc.service.impl;

import com.baobeidaodao.springboot.jdbc.config.DynamicDataSource;
import com.baobeidaodao.springboot.jdbc.service.DynamicDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
@Slf4j
@Service
public class DynamicDataServiceImpl implements DynamicDataService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DynamicDataSource dynamicDataSource;

    @Override
    public List<Map<String, Object>> query(String dataSourceName, String sql) {
        DataSource dataSource = buildDataSource(dataSourceName);
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForList(sql);
    }

    @SuppressWarnings("unchecked")
    private DataSource buildDataSource(String dataSourceName) {
        Map<String, Map<String, String>> datasource = dynamicDataSource.getDatasource();
        Map<String, String> dataSourceMap = datasource.get(dataSourceName);
        if (null == dataSourceMap) {
            return null;
        }
        String driverClassName = dataSourceMap.get("driver-class-name");
        String type = dataSourceMap.get("type");
        Class<? extends DataSource> dataSourceType = null;
        if (null != type) {
            try {
                dataSourceType = (Class<? extends DataSource>) Class.forName(type);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            dataSourceType = DataSourceBuilder.findType(DataSourceBuilder.class.getClassLoader());
        }
        String url = dataSourceMap.get("jdbc-url");
        if (null == url) {
            url = dataSourceMap.get("url");
        }
        String username = dataSourceMap.get("username");
        String password = dataSourceMap.get("password");
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .type(dataSourceType)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

}
