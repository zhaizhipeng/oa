package com.ysdrzp.oa.vo;

import lombok.Data;

@Data
public class UsersSearchVO {

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
     * 机构所属
     */
    private String orgId;

}
