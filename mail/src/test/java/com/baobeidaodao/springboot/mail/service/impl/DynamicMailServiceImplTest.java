package com.baobeidaodao.springboot.mail.service.impl;

import com.baobeidaodao.springboot.mail.service.DynamicMailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicMailServiceImplTest {

    @Resource
    private DynamicMailService dynamicMailService;

    private List<String> to = new ArrayList<>();
    private String subject = "我是主题";
    private String content = "我是内容";
    private List<File> fileList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        to.add("litaoxiaodao@163.com");

        fileList.add(new File("D:\\daodao\\image\\big_face_cat.png"));
        fileList.add(new File("D:\\daodao\\image\\w.jpg"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void send() {
        String sender = "mail-0";
        // sender = "mail-1";
        dynamicMailService.send(sender, to, subject, content, fileList);
    }

}