package com.ysdrzp.oa.vo;

import lombok.Data;

@Data
public class RoleSearchVO {

    private Integer page;

    private Integer limit;

    /**
     * 用户名
     */
    private String roleCnName;

}
