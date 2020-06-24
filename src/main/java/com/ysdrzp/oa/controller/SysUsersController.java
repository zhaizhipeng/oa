package com.ysdrzp.oa.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.RedisUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.constant.YSDRZPConstant;
import com.ysdrzp.oa.service.ISysUsersService;
import com.ysdrzp.oa.vo.UserAddVO;
import com.ysdrzp.oa.vo.UserLoginVO;
import com.ysdrzp.oa.vo.UserRoleEditVO;
import com.ysdrzp.oa.vo.UsersSearchVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 */
@Controller
@RequestMapping("user")
public class SysUsersController {

    @Autowired
    private ISysUsersService sysUsersService;

    @Autowired
    private RedisUtil redisUtil;

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

    /**
     * 编辑用户角色
     * @param userRoleEditVO
     * @return
     */
    @PostMapping("editRole")
    @ResponseBody
    public YSDRZPResult editRole(@RequestBody UserRoleEditVO userRoleEditVO){
        System.out.println(JSONUtil.toJsonStr(userRoleEditVO));
        YSDRZPResult result = sysUsersService.editUserRole(userRoleEditVO);
        return result;
    }

    /**
     * 用户登录
     * @param userLoginVO
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public YSDRZPResult login(@RequestBody UserLoginVO userLoginVO){

        if (StrUtil.isBlank(userLoginVO.getMobilePhone())){
            return YSDRZPResult.ok("手机号不能为空");
        }

        if (StrUtil.isBlank(userLoginVO.getPassword())){
            return YSDRZPResult.ok("密码不能为空");
        }

        YSDRZPResult ysdrzpResult = sysUsersService.login(userLoginVO.getMobilePhone(), userLoginVO.getPassword());
        return ysdrzpResult;
    }

    /**
     * 打开首页
     * @param token
     * @return
     */
    @GetMapping("openIndex")
    public String openIndex(@Param("token") String token, @Param("mobilePhone") String mobilePhone, ModelMap modelMap){

        if (StrUtil.isBlank(token)){
            return "login";
        }

        String key = YSDRZPConstant.USER_LOGIN_TOKEN_INFO + mobilePhone;

        String tokenValue = (String) redisUtil.get(key);
        if (StrUtil.isBlank(tokenValue)){
            // 未登录，或者登录已过期，重新登录
            return "login";
        }

        if (StrUtil.isNotBlank(tokenValue)){
            // 验证token
            String userToken = (String) redisUtil.get(YSDRZPConstant.USER_LOGIN_TOKEN + tokenValue);
            if (token.equals(userToken)){
                return "login";
            }
        }

        modelMap.put("powers", "");
        return "index";
    }

}
