package com.baobeidaodao.springboot.jdbc.service;

import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
public interface MultipleDataService {

    /**
     * 查询 primary 库，输入 sql，返回list
     *
     * @param sql sql
     * @return List
     */
    List<Map<String, Object>> queryPrimary(String sql);

    /**
     * 查询 secondary 库，输入 sql，返回list
     *
     * @param sql sql
     * @return List
     */
    List<Map<String, Object>> querySecondary(String sql);

}
