package com.ysdrzp.oa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {

    private Long id;

    private String roleCnName;

    private String roleEnName;

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