package com.baobeidaodao.springboot.mail.service;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author DaoDao
 * @date 2019-02-13 15:33
 */
public interface MailService {

    /**
     * sendSimpleMailMessage
     *
     * @param to      List<String>
     * @param subject String
     * @param content String
     */
    void sendSimpleMailMessage(List<String> to, String subject, String content);

    /**
     * sendMimeMessage
     *
     * @param to       List<String>
     * @param subject  String
     * @param content  String
     * @param fileList List<File>
     */
    void sendMimeMessage(List<String> to, String subject, String content, List<File> fileList);

    /**
     * send
     *
     * @param javaMailSender   JavaMailSenderImpl
     * @param to       List<String>
     * @param subject  String
     * @param content  String
     * @param fileList List<File>
     */
    void send(JavaMailSenderImpl javaMailSender, List<String> to, String subject, String content, List<File> fileList);

}
