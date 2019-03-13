package com.baobeidaodao.springboot.mail.service.impl;

import com.baobeidaodao.springboot.mail.model.Sender;
import com.baobeidaodao.springboot.mail.service.SenderMailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderMailServiceImplTest {

    private JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    private String host = "smtp.qq.com";
    private String protocol = "smtp";
    private int port = 587;
    private String username = "625117069@qq.com";
    private String password = "password";
    private String encoding = "UTF-8";

    private Map<String, String> map = new HashMap<>();

    private Sender sender = new Sender();

    private List<String> to = new ArrayList<>();
    private String subject = "我是主题";
    private String content = "我是内容";
    private List<File> fileList = new ArrayList<>();

    @Resource
    private SenderMailService senderMailService;

    @Before
    public void setUp() throws Exception {
        map.put("host", host);
        map.put("protocol", protocol);
        map.put("port", String.valueOf(port));
        map.put("username", username);
        map.put("password", password);
        map.put("encoding", encoding);

        javaMailSender = senderMailService.buildJavaMailSender(host, protocol, port, username, password, encoding);

        sender.setHost(host);
        sender.setProtocol(protocol);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setEncoding(encoding);

        to.add("litaoxiaodao@163.com");

        fileList.add(new File("D:\\daodao\\image\\big_face_cat.png"));
        fileList.add(new File("D:\\daodao\\image\\w.jpg"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void buildJavaMailSender() {
        JavaMailSenderImpl javaMailSender = senderMailService.buildJavaMailSender(map);
        Boolean b = senderMailService.testJavaMailSender(javaMailSender);
    }

    @Test
    public void buildJavaMailSender1() {
        JavaMailSenderImpl javaMailSender = senderMailService.buildJavaMailSender(sender);
        Boolean b = senderMailService.testJavaMailSender(javaMailSender);
    }

    @Test
    public void buildJavaMailSender2() {
        JavaMailSenderImpl javaMailSender = senderMailService.buildJavaMailSender(host, protocol, port, username, password, encoding);
        Boolean b = senderMailService.testJavaMailSender(javaMailSender);
    }

    @Test
    public void testJavaMailSender() {
        JavaMailSenderImpl javaMailSender = senderMailService.buildJavaMailSender(host, protocol, port, username, password, encoding);
        Boolean b = senderMailService.testJavaMailSender(javaMailSender);
    }

    @Test
    public void send() {
        JavaMailSenderImpl javaMailSender = senderMailService.buildJavaMailSender(host, protocol, port, username, password, encoding);
        senderMailService.send(javaMailSender, to, subject, content, fileList);
    }

}