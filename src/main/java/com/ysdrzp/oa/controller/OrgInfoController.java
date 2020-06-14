package com.ysdrzp.oa.controller;

import com.ysdrzp.oa.entity.OrgInfo;
import com.ysdrzp.oa.service.IOrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orgInfo")
public class OrgInfoController {

    @Autowired
    private IOrgInfoService orgInfoService;

    @GetMapping("/list")
    @ResponseBody
    public OrgInfo list(){
        return orgInfoService.selectByPrimaryKey(1l);
    }
}
