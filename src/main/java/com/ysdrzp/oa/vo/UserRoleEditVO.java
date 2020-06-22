package com.ysdrzp.oa.vo;

import lombok.Data;

@Data
public class UserRoleEditVO {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 角色Ids
     */
    private Long[] roleIds;
}
