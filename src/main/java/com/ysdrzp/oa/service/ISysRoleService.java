package com.ysdrzp.oa.service;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.entity.SysRole;
import com.ysdrzp.oa.vo.RoleAddVO;
import com.ysdrzp.oa.vo.RoleSearchVO;

/**
 * 角色管理接口
 */
public interface ISysRoleService extends IBaseService<SysRole>{

    /**
     * 分页查询角色列表
     * @param roleSearchVO
     * @return
     */
    YSDRZPResult getList(RoleSearchVO roleSearchVO);

    /**
     * 获取所有角色
     * @return
     */
    YSDRZPResult getAllRoles();

    /**
     * 新增角色
     * @param roleAddVO
     * @return
     */
    YSDRZPResult addRole(RoleAddVO roleAddVO);

    /**
     * 启用角色
     * @param id
     * @return
     */
    YSDRZPResult enableRole(Long id);

    /**
     * 禁用角色
     * @param id
     * @return
     */
    YSDRZPResult disableRole(Long id);

    /**
     * 删除角色
     * @param id
     * @return
     */
    YSDRZPResult delRole(Long id);

    /**
     * 获取角色下拉列表
     * @return
     */
    YSDRZPResult selectList();

}
