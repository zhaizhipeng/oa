package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.entity.SysRoleResources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleResourcesMapper extends IBaseMapper<SysRoleResources>{

    /**
     * 获取角色授权资源列表
     * @param roleId
     * @return
     */
    List<SysRoleResources> selectByRoleId(@Param("roleId") Long roleId);

    /**
     * 删除角色的资源授权记录
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);

    /**
     * 角色授权资源
     * @param sysRoleResourcesList
     * @return
     */
    int batchInsertRoleResources(List<SysRoleResources> sysRoleResourcesList);
}