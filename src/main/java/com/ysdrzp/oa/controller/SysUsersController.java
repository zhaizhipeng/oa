package com.ysdrzp.oa.controller;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.service.ISysUsersService;
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
     * @param usersSearchVo
     * @return
     */
    @PostMapping("list")
    @ResponseBody
    public YSDRZPResult list(@RequestBody UsersSearchVO usersSearchVo){

        YSDRZPResult result = sysUsersService.getList(usersSearchVo);
        return result;
    }

}
