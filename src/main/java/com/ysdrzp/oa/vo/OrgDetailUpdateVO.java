package com.ysdrzp.oa.vo;

import lombok.Data;

/**
 * 更新机构详情
 */
@Data
public class OrgDetailUpdateVO {

    /**
     * 机构主键
     */
    private Long id;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构简称
     */
    private String subName;
}
