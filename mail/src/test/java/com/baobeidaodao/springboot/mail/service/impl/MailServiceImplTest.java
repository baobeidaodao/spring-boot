package com.baobeidaodao.springboot.mail.service.impl;

import com.baobeidaodao.springboot.mail.service.MailService;
import com.baobeidaodao.springboot.mail.service.SenderMailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
public class MailServiceImplTest {

    @Resource
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private SenderMailService senderMailService;

    private String host = "smtp.qq.com";
    private String protocol = "smtp";
    private int port = 587;
    private String username = "625117069@qq.com";
    private String password = "password";
    private String encoding = "UTF-8";

    private JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    private List<String> to = new ArrayList<>();
    private String subject = "我是主题";
    private String content = "我是内容";

    private String template = "mail";
    private Map<String, Object> variables = new HashMap<>();
    private List<File> fileList = new ArrayList<>();

    @Before
    public void setUp() {

        javaMailSender = senderMailService.buildJavaMailSender(host, protocol, port, username, password, encoding);

        to.add("litaoxiaodao@163.com");
        to.add("15801640416@qq.com");
        variables.put("text", "汪汪汪");

        fileList.add(new File("D:\\daodao\\image\\big_face_cat.png"));
        fileList.add(new File("D:\\daodao\\image\\w.jpg"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void sendSimpleMailMessage() {
        mailService.sendSimpleMailMessage(to, subject, content);
    }

    @Test
    public void sendMimeMessage() {
        Context context = new Context();
        context.setVariables(variables);
        String content = templateEngine.process(template, context);
        mailService.sendMimeMessage(to, subject, content, fileList);
    }

    @Test
    public void send() {
        mailService.send(javaMailSender, to, subject, content, fileList);
    }

}