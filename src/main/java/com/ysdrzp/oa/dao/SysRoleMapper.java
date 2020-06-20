package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.entity.SysRole;
import com.ysdrzp.oa.vo.RoleSearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends IBaseMapper<SysRole>{

    /**
     * 获取角色分页列表
     * @param roleSearchVO
     * @return
     */
    List<SysRole> getlist(RoleSearchVO roleSearchVO);

    /**
     * 根据角色中文名查询角色信息
     * @param roleCnName
     * @return
     */
    SysRole selectRoleIsExist(@Param("roleCnName") String roleCnName);
}