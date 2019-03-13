package com.baobeidaodao.springboot.mail.model;

import lombok.Data;

/**
 * @author DaoDao
 */
@Data
public class Sender {
    private String host;
    private String protocol;
    private Integer port;
    private String username;
    private String password;
    private String encoding;
}
