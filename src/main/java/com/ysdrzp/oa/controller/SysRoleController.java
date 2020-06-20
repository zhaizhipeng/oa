package com.ysdrzp.oa.controller;

import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.service.ISysRoleService;
import com.ysdrzp.oa.vo.RoleAddVO;
import com.ysdrzp.oa.vo.RoleSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 */
@Controller
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

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

}
