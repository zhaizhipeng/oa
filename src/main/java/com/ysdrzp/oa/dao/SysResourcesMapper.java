package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.dto.result.MenuTreeDTO;
import com.ysdrzp.oa.entity.SysResources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourcesMapper extends IBaseMapper<SysResources>{

    /**
     * 根据资源名称获取资源信息
     * @param resourcesName
     * @return
     */
    SysResources selectByResourcesName(@Param("resourcesName") String resourcesName);

    /**
     * 获取资源树
     * @param id
     * @return
     */
    List<MenuTreeDTO> getMenuTree(Long id);

    /**
     * 获取资源详情
     * @param id
     * @return
     */
    MenuTreeDTO selectResourcesDetail(Long id);

    /**
     * 根据父资源下的所有子级菜单
     * @param fatherId
     * @return
     */
    List<SysResources> selectByFatherId(@Param("fatherId") Long fatherId);
}