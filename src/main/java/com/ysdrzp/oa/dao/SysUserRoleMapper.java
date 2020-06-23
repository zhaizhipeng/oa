package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends IBaseMapper<SysUserRole>{

    /**
     * 用户批量授权角色
     * @param userRoleList
     * @return
     */
    int batchInsert(List<SysUserRole> userRoleList);
}