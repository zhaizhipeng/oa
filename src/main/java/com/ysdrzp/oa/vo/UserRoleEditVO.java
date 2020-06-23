package com.ysdrzp.oa.vo;

import lombok.Data;

@Data
public class UserRoleEditVO {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 角色Ids
     */
    private Long[] roleIds;
}
