package com.baobeidaodao.springboot.mail.service.impl;

import com.baobeidaodao.springboot.mail.model.Sender;
import com.baobeidaodao.springboot.mail.service.MailService;
import com.baobeidaodao.springboot.mail.service.SenderMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
@Slf4j
@Service
public class SenderMailServiceImpl implements SenderMailService {

    @Resource
    private MailService mailService;

    @Override
    public JavaMailSenderImpl buildJavaMailSender(Map<String, String> map) {
        String host = map.get("host");
        String protocol = map.get("protocol");
        Integer port = Integer.valueOf(map.get("port"));
        String username = map.get("username");
        String password = map.get("password");
        String encoding = map.get("encoding");
        return buildJavaMailSender(host, protocol, port, username, password, encoding);
    }

    @Override
    public JavaMailSenderImpl buildJavaMailSender(Sender sender) {
        String host = sender.getHost();
        String protocol = sender.getProtocol();
        Integer port = sender.getPort();
        String username = sender.getUsername();
        String password = sender.getPassword();
        String encoding = sender.getEncoding();
        return buildJavaMailSender(host, protocol, port, username, password, encoding);
    }

    @Override
    public JavaMailSenderImpl buildJavaMailSender(String host, String protocol, Integer port, String username, String password, String encoding) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setProtocol(protocol);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding(encoding);
        return javaMailSender;
    }

    @Override
    public Boolean testJavaMailSender(JavaMailSenderImpl javaMailSender) {
        try {
            javaMailSender.testConnection();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void send(JavaMailSenderImpl javaMailSender, List<String> to, String subject, String content, List<File> fileList) {
        mailService.send(javaMailSender, to, subject, content, fileList);
    }

}
