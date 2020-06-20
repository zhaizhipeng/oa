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

    /**
     * 性别：0：保密、1：男、2：女
     */
    private Integer gender;
}
