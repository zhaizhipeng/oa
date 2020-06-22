package com.ysdrzp.oa.controller;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.service.ISysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管控台
 */
@Controller
@RequestMapping("console")
public class ManagementConsoleController {

    @Autowired
    private ISysUsersService sysUsersService;

    /**
     * 获取性别分布数据
     * @return
     */
    @GetMapping("genderDistribution")
    @ResponseBody
    public YSDRZPResult genderDistribution(){
        YSDRZPResult result = sysUsersService.getGenderDistribution();
        return result;
    }

    /**
     * 获取用户机构分布数据
     * @return
     */
    @GetMapping("orgDistribution")
    @ResponseBody
    public YSDRZPResult orgDistribution(){
        YSDRZPResult result = sysUsersService.getOrgDistribution();
        return result;
    }

}
