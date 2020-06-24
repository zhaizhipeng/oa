package com.ysdrzp.oa.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.entity.SysRoleResources;
import com.ysdrzp.oa.service.ISysRoleResourcesService;
import com.ysdrzp.oa.service.ISysRoleService;
import com.ysdrzp.oa.vo.RoleAddVO;
import com.ysdrzp.oa.vo.RoleResourceEditVO;
import com.ysdrzp.oa.vo.RoleSearchVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理
 */
@Controller
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysRoleResourcesService sysRoleResourcesService;

    /**
     * 获取角色列表
     * @param roleSearchVO
     * @return
     */
    @PostMapping("list")
    @ResponseBody
    public YSDRZPResult list(@RequestBody RoleSearchVO roleSearchVO){
        System.out.println(JSONUtil.toJsonStr(roleSearchVO));
        YSDRZPResult result = sysRoleService.getList(roleSearchVO);
        return result;
    }

    /**
     * 获取角色下拉列表
     * @return
     */
    @PostMapping("selectList")
    @ResponseBody
    public YSDRZPResult selectList(){
        YSDRZPResult result = sysRoleService.selectList();
        return result;
    }

    /**
     * 获取所有角色信息
     * @return
     */
    @PostMapping("allRole")
    @ResponseBody
    public YSDRZPResult allRole(){
        YSDRZPResult result = sysRoleService.getAllRoles();
        return result;
    }

    /**
     * 新增角色
     * @param roleAddVO
     * @return
     */
    @PostMapping("addRole")
    @ResponseBody
    public YSDRZPResult addRole(@RequestBody RoleAddVO roleAddVO){
        System.out.println(JSONUtil.toJsonStr(roleAddVO));
        YSDRZPResult result = sysRoleService.addRole(roleAddVO);
        return result;
    }

    /**
     * 启用角色
     * @param id
     * @return
     */
    @GetMapping("enable")
    @ResponseBody
    public YSDRZPResult enable(@RequestParam Long id){
        System.out.println(id);
        YSDRZPResult result = sysRoleService.enableRole(id);
        return result;
    }

    /**
     * 启用角色
     * @param id
     * @return
     */
    @GetMapping("disable")
    @ResponseBody
    public YSDRZPResult disable(@RequestParam Long id){
        System.out.println(id);
        YSDRZPResult result = sysRoleService.disableRole(id);
        return result;
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @GetMapping("delete")
    @ResponseBody
    public YSDRZPResult delUser(@RequestParam Long id){
        System.out.println(id);
        YSDRZPResult result = sysRoleService.delRole(id);
        return result;
    }

    /**
     * 打开角色资源编辑页面
     * @param roleId
     * @param modelMap
     * @return
     */
    @GetMapping("openRoleResourcesEdit")
    public String openRoleResourcesEdit(@Param("roleId") Long roleId, @Param("roleName") String roleName, ModelMap modelMap){
        modelMap.put("roleId", roleId);
        modelMap.put("roleName", roleName);
        List<SysRoleResources> result = sysRoleResourcesService.getAllRoleResources(roleId);
        if (CollectionUtil.isNotEmpty(result)){
            List currentChecked = new ArrayList<>();
            for (int i = 0; i < result.size(); i++){
                currentChecked.add(result.get(i).getResourcesId());
            }
            modelMap.put("currentChecked", JSONUtil.toJsonStr(currentChecked.toArray()));
        }

        return "application/sys/role/role_edit";
    }

    @PostMapping("editRoleResources")
    @ResponseBody
    public YSDRZPResult editRoleResources(@RequestBody RoleResourceEditVO roleResourceEditVO){

        YSDRZPResult ysdrzpResult = sysRoleResourcesService.editRoleResource(roleResourceEditVO);
        return ysdrzpResult;
    }

}
