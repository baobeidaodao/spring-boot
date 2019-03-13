package com.baobeidaodao.springboot.mail.service;

import java.io.File;
import java.util.List;

/**
 * @author DaoDao
 */
public interface DynamicMailService {

    /**
     * send
     *
     * @param sender   String
     * @param to       List<String>
     * @param subject  String
     * @param content  String
     * @param fileList List<File>
     */
    void send(String sender, List<String> to, String subject, String content, List<File> fileList);

}
