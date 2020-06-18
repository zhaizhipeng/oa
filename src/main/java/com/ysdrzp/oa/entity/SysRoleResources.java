package com.ysdrzp.oa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoleResources {

    private Long id;

    private Long roleId;

    private Long resourcesId;

    private String miscDesc;

    private Integer status;

    private Date createTime;

    private Long createOperId;

    private String createOperName;

    private Date updateTime;

    private Long updateOperId;

    private String updateOperName;

}