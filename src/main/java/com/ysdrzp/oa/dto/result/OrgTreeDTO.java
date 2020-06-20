package com.ysdrzp.oa.dto.result;

import lombok.Data;

import java.util.List;

/**
 * 机构树
 */
@Data
public class OrgTreeDTO {

    /**
     * 机构主键ID
     */
    private Long id;

    /**
     * 机构Id
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构简称
     */
    private String subName;

    /**
     * 上级机构
     */
    private OrgTreeDTO parent;

    /**
     * 子级机构列表
     */
    List<OrgTreeDTO> children;

}
