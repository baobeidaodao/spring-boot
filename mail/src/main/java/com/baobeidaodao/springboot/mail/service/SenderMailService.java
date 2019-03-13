package com.baobeidaodao.springboot.mail.service;

import com.baobeidaodao.springboot.mail.model.Sender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
public interface SenderMailService {

    JavaMailSenderImpl buildJavaMailSender(Map<String, String> map);

    JavaMailSenderImpl buildJavaMailSender(Sender sender);

    JavaMailSenderImpl buildJavaMailSender(String host, String protocol, Integer port, String username, String password, String encoding);

    Boolean testJavaMailSender(JavaMailSenderImpl javaMailSender);

    void send(JavaMailSenderImpl javaMailSender, List<String> to, String subject, String content, List<File> fileList);

}
