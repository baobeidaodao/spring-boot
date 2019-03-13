package com.baobeidaodao.springboot.jdbc.service.impl;

import com.baobeidaodao.springboot.jdbc.service.MultipleDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
@Slf4j
@Service
public class MultipleDataServiceImpl implements MultipleDataService {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Resource
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryJdbcTemplate;

    @Override
    public List<Map<String, Object>> queryPrimary(String sql) {
        return primaryJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> querySecondary(String sql) {
        return secondaryJdbcTemplate.queryForList(sql);
    }

}
