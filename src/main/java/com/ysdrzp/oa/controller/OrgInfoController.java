package com.ysdrzp.oa.controller;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.service.IOrgInfoService;
import com.ysdrzp.oa.vo.OrgSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orgInfo")
public class OrgInfoController {

    @Autowired
    private IOrgInfoService orgInfoService;

    /**
     * 获取机构列表
     * @param orgSearchVo
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public YSDRZPResult list(@RequestBody OrgSearchVo orgSearchVo){

        YSDRZPResult result = orgInfoService.getList(orgSearchVo);
        return result;
    }

}
