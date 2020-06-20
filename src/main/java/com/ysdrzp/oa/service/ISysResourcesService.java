package com.ysdrzp.oa.service;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dto.result.MenuTreeDTO;
import com.ysdrzp.oa.entity.SysResources;
import com.ysdrzp.oa.vo.ResourcesSaveVO;
import com.ysdrzp.oa.vo.ResourcesUpdateVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单资源管理接口
 */
public interface ISysResourcesService extends IBaseService<SysResources>{

    /**
     * 获取菜单信息
     * @param id
     * @return
     */
    SysResources getResources(Long id);

    /**
     * 根据资源名称查询资源信息
     * @param resourcesName
     * @return
     */
    SysResources selectByResourcesName(String resourcesName);

    /**
     * 获取菜单资源树
     * @return
     * @param id
     */
    List<HashMap<String, Object>> getMenuTree(@Param("id") Long id);

    /**
     * 获取资源详情
     * @param id
     * @return
     */
    MenuTreeDTO getMenuDetail(Long id);

    /**
     * 更新资源
     * @param resourcesUpdateVO
     * @return
     */
    YSDRZPResult updateResources(ResourcesUpdateVO resourcesUpdateVO);

    /**
     * 删除资源
     * @param id
     * @return
     */
    YSDRZPResult deleteResources(Long id);

    /**
     * 添加资源
     * @param resourcesSaveVO
     * @return
     */
    YSDRZPResult saveResources(ResourcesSaveVO resourcesSaveVO);
}
