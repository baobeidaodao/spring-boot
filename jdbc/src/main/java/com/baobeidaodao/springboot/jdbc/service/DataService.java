package com.baobeidaodao.springboot.jdbc.service;

import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
public interface DataService {

    /**
     * 查询默认库，传入 sql, 返回 list 数据
     *
     * @param sql sql
     * @return list
     */
    List<Map<String, Object>> query(String sql);

}
