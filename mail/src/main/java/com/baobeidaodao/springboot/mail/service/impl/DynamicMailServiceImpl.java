package com.baobeidaodao.springboot.mail.service.impl;

import com.baobeidaodao.springboot.mail.config.DynamicMail;
import com.baobeidaodao.springboot.mail.service.DynamicMailService;
import com.baobeidaodao.springboot.mail.service.MailService;
import com.baobeidaodao.springboot.mail.service.SenderMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 */
@Slf4j
@Service
public class DynamicMailServiceImpl implements DynamicMailService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private DynamicMail dynamicMail;

    @Resource
    private MailService mailService;

    @Resource
    private SenderMailService senderMailService;

    @Override
    public void send(String sender, List<String> to, String subject, String content, List<File> fileList) {
        if (null == sender || sender.isEmpty()) {
            mailService.sendMimeMessage(to, subject, content, fileList);
            return;
        }
        Map<String, Map<String, String>> mailMap = dynamicMail.getMail();
        Map<String, String> mail = mailMap.get(sender);
        JavaMailSenderImpl javaMailSender = senderMailService.buildJavaMailSender(mail);
        mailService.send(javaMailSender, to, subject, content, fileList);
    }
}
