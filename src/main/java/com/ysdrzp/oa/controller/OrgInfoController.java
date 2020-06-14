package com.ysdrzp.oa.controller;

import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.Page;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.entity.OrgInfo;
import com.ysdrzp.oa.service.IOrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orgInfo")
public class OrgInfoController {

    @Autowired
    private IOrgInfoService orgInfoService;

    /**
     * 获取机构列表
     * @param page
     * @param model
     * @return
     */
    @PostMapping("/list")
    public String list(Page page, Model model){

        PageInfo<OrgInfo> pageInfo = orgInfoService.getList(page);
        model.addAttribute("pageInfo", pageInfo);
        return "org/org_list";
    }

    @GetMapping("/addOrg")
    public String addOrg(){
        return "org/addOrg";
    }

    /**
     * 新增机构
     * @return
     */
    @PostMapping("/addOrg")
    public YSDRZPResult addOrg(OrgInfo orgInfo){
        OrgInfo orgInfo1 = orgInfoService.selectByOrgName(orgInfo.getOrgName());
        if (orgInfo1 != null){
            return YSDRZPResult.fail;
        }
        orgInfoService.insertSelective(orgInfo);
        return YSDRZPResult.success;
    }

    @GetMapping("/editOrg")
    public String editOrg(){
        return "org/editOrg";
    }

    /**
     * 编辑机构
     * @return
     */
    @PostMapping("/editOrg")
    public YSDRZPResult editOrg(OrgInfo orgInfo){
        OrgInfo orgInfo1 = orgInfoService.selectByPrimaryKey(orgInfo.getId());
        if (orgInfo1 == null){
            return YSDRZPResult.fail;
        }
        orgInfoService.updateByPrimaryKey(orgInfo);
        return YSDRZPResult.success;
    }

    @GetMapping("/delOrg")
    public String delOrg(){
        return "org/delOrg";
    }

    /**
     * 删除机构
     * @return
     */
    @PostMapping("/delOrg")
    public YSDRZPResult delOrg(Long id){
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(id);
        if (orgInfo == null){
            return YSDRZPResult.fail;
        }
        orgInfoService.deleteByPrimaryKey(id);
        return YSDRZPResult.success;
    }

}
