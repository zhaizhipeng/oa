package com.ysdrzp.oa.service;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.entity.SysRoleResources;
import com.ysdrzp.oa.vo.RoleResourceEditVO;

import java.util.List;

/**
 * 角色资源授权管理
 */
public interface ISysRoleResourcesService extends IBaseService<SysRoleResources>{

    /**
     * 获取角色被授权的资源信息
     * @param roleId
     * @return
     */
    List<SysRoleResources> getAllRoleResources(Long roleId);

    /**
     * 角色授权资源
     * @param roleResourceEditVO
     * @return
     */
    YSDRZPResult editRoleResource(RoleResourceEditVO roleResourceEditVO);
}
