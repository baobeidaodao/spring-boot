package com.baobeidaodao.springboot.mail.service.impl;

import com.baobeidaodao.springboot.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * @author DaoDao
 * @date 2019-02-13 15:33
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMailMessage(List<String> to, String subject, String content) {
        String[] t = new String[to.size()];
        t = to.toArray(t);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(t);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
            log(from, to, subject, true);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            log(from, to, subject, false);
        }
    }

    @Override
    public void sendMimeMessage(List<String> to, String subject, String content, List<File> fileList) {
        String[] t = new String[to.size()];
        t = to.toArray(t);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(t);
            mimeMessageHelper.setSubject(subject);
            if (null == content) {
                content = "";
            }
            mimeMessageHelper.setText(content, true);
            for (File file : fileList) {
                String filePath = file.getPath();
                String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                mimeMessageHelper.addAttachment(fileName, file);
            }
            javaMailSender.send(mimeMessage);
            log(from, to, subject, true);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            log(from, to, subject, false);
        }
    }

    @Override
    public void send(JavaMailSenderImpl javaMailSender, List<String> to, String subject, String content, List<File> fileList) {
        this.javaMailSender = javaMailSender;
        this.from = javaMailSender.getUsername();
        sendMimeMessage(to, subject, content, fileList);
    }

    private void log(String from, List<String> to, String subject, boolean ok) {
        String status = ok ? "success" : "fail";
        StringBuilder stringBuilder = new StringBuilder();
        for (String t : to) {
            stringBuilder.append(t);
            stringBuilder.append(";");
        }
        String toString = stringBuilder.toString();
        String text = "send mail " + status + "!!! from: " + from + " to: " + toString + " subject: " + subject;
        if (ok) {
            log.info(text);
        } else {
            log.error(text);
        }
    }

}
