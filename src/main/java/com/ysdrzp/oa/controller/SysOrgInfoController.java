package com.ysdrzp.oa.controller;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.service.ISysOrgInfoService;
import com.ysdrzp.oa.vo.OrgSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 机构管理
 */
@Controller
@RequestMapping("orgInfo")
public class SysOrgInfoController {

    @Autowired
    private ISysOrgInfoService sysOrgInfoService;

    /**
     * 获取机构列表
     * @param orgSearchVO
     * @return
     */
    @PostMapping("list")
    @ResponseBody
    public YSDRZPResult list(@RequestBody OrgSearchVO orgSearchVO){

        YSDRZPResult result = sysOrgInfoService.getList(orgSearchVO);
        return result;
    }

}
