package com.ysdrzp.oa.controller;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dto.result.MenuTreeDTO;
import com.ysdrzp.oa.entity.SysResources;
import com.ysdrzp.oa.service.ISysResourcesService;
import com.ysdrzp.oa.vo.ResourcesSaveVO;
import com.ysdrzp.oa.vo.ResourcesUpdateVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单资源管理
 */
@Controller
@RequestMapping("menu")
public class SysResourcesController {

    @Autowired
    private ISysResourcesService sysResourcesService;

    /**
     * 获取菜单资源组织树
     * @return
     */
    @PostMapping("menuTree")
    @ResponseBody
    public List<HashMap<String, Object>> menuTree(){
        List<HashMap<String, Object>> result = sysResourcesService.getMenuTree(null);
        return result;
    }

    /**
     * 获取菜单资源组织树
     * @return
     */
    @GetMapping("roleMenuTree")
    @ResponseBody
    public List<HashMap<String, Object>> menuTree(@Param("roleId") Long roleId){
        List<HashMap<String, Object>> result = sysResourcesService.getRoleMenuTree(roleId);
        return result;
    }

    /**
     * 渲染菜单资源详情
     * @return
     */
    @RequestMapping("detail")
    public String detail(@RequestParam("id") Long id, ModelMap modelMap){
        MenuTreeDTO menuTreeDTO = sysResourcesService.getMenuDetail(id);
        String fatherResourcesName = menuTreeDTO.getParent() == null ? "" : menuTreeDTO.getParent().getResourcesName();

        modelMap.put("fatherResourcesName", fatherResourcesName);
        modelMap.put("menuDetail", menuTreeDTO);
        return "/sys/menu/menu_detail";
    }

    /**
     * 修改菜单资源信息
     * @param resourcesUpdateVO
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public YSDRZPResult update(@RequestBody ResourcesUpdateVO resourcesUpdateVO){
        YSDRZPResult ysdrzpResult = sysResourcesService.updateResources(resourcesUpdateVO);
        return ysdrzpResult;
    }

    /**
     * 删除菜单资源信息
     * @param resourcesUpdateVO
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public YSDRZPResult delete(@RequestBody ResourcesUpdateVO resourcesUpdateVO){
        YSDRZPResult ysdrzpResult = sysResourcesService.deleteResources(resourcesUpdateVO.getId());
        return ysdrzpResult;
    }

    /**
     * 获取菜单资源添加页面和数据信息
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("getAdd")
    public String getAdd(@RequestParam(value = "id") Long id, ModelMap modelMap){
        SysResources sysResources = sysResourcesService.getResources(id);
        modelMap.put("fatherId", id);
        modelMap.put("fatherMenuName", sysResources.getResourcesName());
        return "/sys/menu/menu_add";
    }

    /**
     * 新增菜单资源
     * @param resourcesSaveVO
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public YSDRZPResult add(@RequestBody ResourcesSaveVO resourcesSaveVO){
        YSDRZPResult ysdrzpResult = sysResourcesService.saveResources(resourcesSaveVO);
        return ysdrzpResult;
    }

}
