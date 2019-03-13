package com.baobeidaodao.springboot.jdbc.service.impl;

import com.baobeidaodao.springboot.jdbc.service.DynamicDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataServiceImplTest {

    @Resource
    private DynamicDataService dynamicDataService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void query() {
        String dataSourceName = "datasource-0";
        // dataSourceName = "datasource-1";
        String sql = "select * from test";
        List<Map<String, Object>> data = dynamicDataService.query(dataSourceName, sql);
    }

}