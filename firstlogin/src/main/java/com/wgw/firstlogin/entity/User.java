package com.wgw.firstlogin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guanwu
 * @created on 2022-10-28 11:27:15
 **/

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8547000306592772945L;
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
}
