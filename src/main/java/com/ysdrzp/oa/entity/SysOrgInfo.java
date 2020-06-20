package com.ysdrzp.oa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysOrgInfo {

    private Long id;

    private String orgId;

    private String orgName;

    private String subName;

    private String fatherOrgId;

    private String fatherOrgName;

    private Integer disabled;

    private String miscDesc;

    private Integer status;

    private Date createTime;

    private Long createOperId;

    private String createOperName;

    private Date updateTime;

    private Long updateOperId;

    private String updateOperName;

}