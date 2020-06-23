package com.ysdrzp.oa.controller;

import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.service.ISysUsersService;
import com.ysdrzp.oa.vo.UserAuthSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户授权管理管理
 */
@Controller
@RequestMapping("authorization")
public class SysUserAuthorizationController {

    @Autowired
    private ISysUsersService sysUsersService;

    /**
     * 获取用户授权页面
     * @param modelMap
     * @return
     */
    @GetMapping("getUserAuthEdit")
    public String getUserAuthEdit(@RequestParam("mobilePhone") String mobilePhone, @RequestParam("userName") String userName,
                                  @RequestParam("userId") Long userId, ModelMap modelMap){

        modelMap.put("mobilePhone", mobilePhone);
        modelMap.put("userName", userName);
        modelMap.put("userId", userId);
        List<Long> roles = sysUsersService.getRoleIdsByUserId(userId);
        modelMap.put("currentRoleIds", JSONUtil.toJsonStr(roles.toArray()));
        return "sys/authorization/user_auth_edit";
    }

    @PostMapping("userAuthList")
    @ResponseBody
    public YSDRZPResult userAuthList(@RequestBody UserAuthSearchVO userAuthSearchVO){
        YSDRZPResult ysdrzpResult = sysUsersService.userAuthList(userAuthSearchVO);
        return ysdrzpResult;
    }

}
