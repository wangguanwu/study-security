package com.wgw.firstlogin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author guanwu
 * @created on 2022-10-28 14:23:09
 **/

@Data
public class Permission implements Serializable {


    private static final long serialVersionUID = 6290107560041284775L;
    private Long id;

    private Long parentId;

    private String name;

    private String enname;

    private String description;

    private Date created;

    private Date updated;
}
