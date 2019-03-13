package com.baobeidaodao.springboot.mail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author DaoDao
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.dynamic-mail")
public class DynamicMail {
    private Map<String, Map<String, String>> mail;
}
