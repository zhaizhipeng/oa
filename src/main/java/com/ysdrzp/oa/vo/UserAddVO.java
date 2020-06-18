package com.ysdrzp.oa.vo;

import lombok.Data;

@Data
public class UserAddVO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 备注
     */
    private String miscDesc;
}
