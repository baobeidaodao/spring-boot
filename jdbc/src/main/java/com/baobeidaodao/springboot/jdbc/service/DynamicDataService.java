package com.baobeidaodao.springboot.jdbc.service;

import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
public interface DynamicDataService {

    /**
     * 查询动态配置的数据源，传入 数据源名称 和 sql, 返回 list 数据
     *
     * @param dataSourceName 数据源名称，再配置文件中动态配置一组数据源
     * @param sql            sql
     * @return list
     */
    List<Map<String, Object>> query(String dataSourceName, String sql);

}
