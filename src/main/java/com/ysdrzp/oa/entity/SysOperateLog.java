package com.ysdrzp.oa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysOperateLog {

    private Long id;

    private Integer operateType;

    private String oldRoleIds;

    private String roleIds;

    private String oldRoleNames;

    private String roleNames;

    private String oldResourcesNames;

    private String resourcesNames;

    private String userName;

    private String content;

    private String miscDesc;

    private Integer status;

    private Date createTime;

    private Long createOperId;

    private String createOperName;

    private Date updateTime;

    private Long updateOperId;

    private String updateOperName;

}