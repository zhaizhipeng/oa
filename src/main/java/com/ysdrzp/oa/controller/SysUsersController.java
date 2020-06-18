package com.ysdrzp.oa.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.service.ISysUsersService;
import com.ysdrzp.oa.vo.UserAddVO;
import com.ysdrzp.oa.vo.UsersSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
