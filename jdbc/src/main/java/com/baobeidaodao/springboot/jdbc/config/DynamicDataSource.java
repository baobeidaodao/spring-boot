package com.baobeidaodao.springboot.jdbc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author DaoDao
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.dynamic-datasource")
public class DynamicDataSource {
    private Map<String, Map<String, String>> datasource;
}
