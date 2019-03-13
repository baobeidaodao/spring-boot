package com.baobeidaodao.springboot.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author DaoDao
 * @date 2019-02-13 11:53
 */
@Slf4j
@RestController
@RequestMapping(value = "file")
public class FileController {

    @RequestMapping(value = "download", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Resource> export(HttpServletRequest request) {
        String fileName = request.getParameter("fileName");
        String filePath = "D:\\" + fileName;
        File file = new File(filePath);
        Resource body = new FileSystemResource(file);
        String header = request.getHeader("User-Agent").toUpperCase();
        HttpStatus status = HttpStatus.CREATED;
        try {
            String msie = "MSIE";
            String trident = "TRIDENT";
            String edge = "EDGE";
            if (header.contains(msie) || header.contains(trident) || header.contains(edge)) {
                fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
                // IE下载文件名空格变+号问题
                fileName = fileName.replace("+", "%20");
                status = HttpStatus.OK;
            } else {
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(file.length());
        return new ResponseEntity<>(body, headers, status);
    }

}
