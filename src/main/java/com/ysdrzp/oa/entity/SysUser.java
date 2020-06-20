package com.ysdrzp.oa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    private Long id;

    private String mobilePhone;

    private String password;

    private Date pwdValidDate;

    private String mail;

    private String orgId;

    private String userName;

    private Integer gender;

    private Integer disabled;

    private Date lastLoginDate;

    private String miscDesc;

    private Integer status;

    private Date createTime;

    private Long createOperId;

    private String createOperName;

    private Date updateTime;

    private Long updateOperId;

    private String updateOperName;

}