package com.wgw.firstlogin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author guanwu
 * @created on 2022-10-28 14:25:13
 **/
public class Role implements Serializable {
    private static final long serialVersionUID = -3348165613079409182L;

    private Long id;

    private Long parentId;

    private String name;

    private String enname;

    private String description;

    private Date created;

    private Date updated;
}
