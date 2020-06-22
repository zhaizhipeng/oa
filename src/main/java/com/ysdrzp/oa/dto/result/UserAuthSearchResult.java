package com.ysdrzp.oa.dto.result;

import lombok.Data;

/**
 * 用户列表查询结果
 */
@Data
public class UserAuthSearchResult {

    /** 用户id **/
    private Long userId;

    /** 用户名称 */
    private String userName;

    /** 是否禁用（0：禁用，1：启用） */
    private Integer disabled;

    /** 联系电话 */
    private String mobilePhone;

    /** 机构名称 */
    private String orgName;

    /** 关联角色组 列**/
    private Object[] roleCnNames;

}
