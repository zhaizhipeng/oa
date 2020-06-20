package com.ysdrzp.oa.vo;

import lombok.Data;

@Data
public class RoleAddVO {

    /**
     * 角色中文名
     */
    private String roleCnName;

    /**
     * 角色英文名
     */
    private String roleEnName;

    /**
     * 备注
     */
    private String miscDesc;
}
