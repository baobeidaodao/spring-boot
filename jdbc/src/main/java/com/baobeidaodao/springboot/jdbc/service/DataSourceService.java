package com.baobeidaodao.springboot.jdbc.service;

import com.baobeidaodao.springboot.jdbc.model.DataSourceModel;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
public interface DataSourceService {

    DataSource buildDataSource(Map<String, String> map);

    DataSource buildDataSource(DataSourceModel dataSourceModel);

    DataSource buildDataSource(String url, String username, String password, String driverClassName, String type);

    Boolean testDataSource(DataSource dataSource);

    List<Map<String, Object>> query(DataSource dataSource, String sql);

}
