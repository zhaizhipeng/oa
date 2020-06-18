package com.ysdrzp.oa.dto.result;

import lombok.Data;

import java.util.Date;

@Data
public class SysUsersSearchDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 备注
     */
    private String miscDesc;

    /**
     * 禁用/启用 0：禁用、1：启用
     */
    private Integer disabled;

    /**
     * 机构所属
     */
    private String orgName;

    /**
     * 上次登录时间
     */
    private Date lastLoginDate;

    /**
     * 密码有效日期
     */
    private Date pwdValidDate;

}
