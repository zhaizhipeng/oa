package com.ysdrzp.oa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysResources {

    private Long id;

    private String resourcesName;

    private String resourcesKey;

    private Integer resourcesType;

    private Integer urlType;

    private Long fatherId;

    private String miscDesc;

    private Integer status;

    private Date createTime;

    private Long createOperId;

    private String createOperName;

    private Date updateTime;

    private Long updateOperId;

    private String updateOperName;

}