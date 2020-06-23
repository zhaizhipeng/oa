package com.ysdrzp.oa.vo;

import lombok.Data;

/**
 * 角色资源管理
 */
@Data
public class RoleResourceEditVO {

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 资源Ids
     */
    private String resourcesIds;
}
