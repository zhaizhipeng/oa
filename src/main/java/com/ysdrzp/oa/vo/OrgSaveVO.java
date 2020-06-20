package com.ysdrzp.oa.vo;

import lombok.Data;

@Data
public class OrgSaveVO {

    /**
     * 父机构Id
     */
    private Long fatherId;

    /**
     * 父机构OrgId
     */
    private String fatherOrgId;

    /**
     * 父机构OrgName
     */
    private String fatherOrgName;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构简称
     */
    private String subName;

    /**
     * 机构描述信息
     */
    private String miscDesc;

}
