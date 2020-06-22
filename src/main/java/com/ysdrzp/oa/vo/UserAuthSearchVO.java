package com.ysdrzp.oa.vo;

import lombok.Data;

@Data
public class UserAuthSearchVO {

    private Integer page;

    private Integer limit;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 机构所属Id
     */
    private String orgId;

    /**
     * 角色Id
     */
    private Long roleId;
}
