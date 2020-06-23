package com.ysdrzp.oa.service;

import com.ysdrzp.oa.entity.SysUserRole;

import java.util.List;

/**
 * 用户授权角色接口
 */
public interface ISysUserRoleService extends IBaseService<SysUserRole>{

    /**
     * 批量插入用户角色
     * @param userRoleList
     * @return
     */
    int batchInsertUserRole(List<SysUserRole> userRoleList);

}
