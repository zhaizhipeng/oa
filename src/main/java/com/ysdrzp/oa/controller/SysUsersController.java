package com.ysdrzp.oa.controller;

import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.service.ISysUsersService;
import com.ysdrzp.oa.vo.UserAddVO;
import com.ysdrzp.oa.vo.UsersSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 */
@Controller
@RequestMapping("user")
public class SysUsersController {

    @Autowired
    private ISysUsersService sysUsersService;

    /**
     * 获取机构列表
     * @param usersSearchVO
     * @return
     */
    @PostMapping("list")
    @ResponseBody
    public YSDRZPResult list(@RequestBody UsersSearchVO usersSearchVO){
        System.out.println(JSONUtil.toJsonStr(usersSearchVO));
        YSDRZPResult result = sysUsersService.getList(usersSearchVO);
        return result;
    }

    /**
     * 新增用户
     * @param userAddVO
     * @return
     */
    @PostMapping("addUser")
    @ResponseBody
    public YSDRZPResult addUser(@RequestBody UserAddVO userAddVO){
        System.out.println(JSONUtil.toJsonStr(userAddVO));
        YSDRZPResult result = sysUsersService.addUser(userAddVO);
        return result;
    }

    /**
     * 启用用户
     * @param id
     * @return
     */
    @GetMapping("enable")
    @ResponseBody
    public YSDRZPResult enable(@RequestParam Long id){
        System.out.println(id);
        YSDRZPResult result = sysUsersService.enableUser(id);
        return result;
    }

    /**
     * 启用用户
     * @param id
     * @return
     */
    @GetMapping("disable")
    @ResponseBody
    public YSDRZPResult disable(@RequestParam Long id){
        System.out.println(id);
        YSDRZPResult result = sysUsersService.disableUser(id);
        return result;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("delete")
    @ResponseBody
    public YSDRZPResult delUser(@RequestParam Long id){
        System.out.println(id);
        YSDRZPResult result = sysUsersService.delUser(id);
        return result;
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @GetMapping("pwdReset")
    @ResponseBody
    public YSDRZPResult pwdReset(@RequestParam Long id){
        System.out.println(id);
        YSDRZPResult result = sysUsersService.pwdReset(id);
        return result;
    }

}
