package com.ysdrzp.oa.controller;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dto.result.OrgTreeDTO;
import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.service.ISysOrgInfoService;
import com.ysdrzp.oa.vo.OrgSaveVO;
import com.ysdrzp.oa.vo.OrgUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 机构管理
 */
@Controller
@RequestMapping("org")
public class SysOrgInfoController {

    @Autowired
    private ISysOrgInfoService sysOrgInfoService;

    /**
     * 获取机构组织树
     * @return
     */
    @PostMapping("orgTree")
    @ResponseBody
    public List<HashMap<String, Object>> orgTree(){
        List<HashMap<String, Object>> result = sysOrgInfoService.getOrgTree(null);
        return result;
    }

    /**
     * 获取机构下拉树
     * @return
     */
    @PostMapping("orgTreeSelect")
    @ResponseBody
    public List<HashMap<String, Object>> orgTreeSelect(){
        List<HashMap<String, Object>> result = sysOrgInfoService.getOrgTreeSelect(null);
        return result;
    }


    /**
     * 渲染机构详情
     * @return
     */
    @RequestMapping("detail")
    public String detail(@RequestParam("id") Long id, ModelMap modelMap){
        OrgTreeDTO orgTreeDTO = sysOrgInfoService.getOrgDetail(id);
        String parentOrgName = orgTreeDTO.getParent() == null ? "" : orgTreeDTO.getParent().getOrgName();

        modelMap.put("parentOrgName", parentOrgName);
        modelMap.put("orgDetail", orgTreeDTO);
        return "application/sys/org/org_detail";
    }

    /**
     * 修改机构信息
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public YSDRZPResult update(@RequestBody OrgUpdateVO orgUpdateVO){
        YSDRZPResult ysdrzpResult = sysOrgInfoService.updateOrgInfo(orgUpdateVO);
        return ysdrzpResult;
    }

    /**
     * 删除机构信息
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public YSDRZPResult delete(@RequestBody OrgUpdateVO orgUpdateVO){
        YSDRZPResult ysdrzpResult = sysOrgInfoService.deleteOrgInfo(orgUpdateVO.getId());
        return ysdrzpResult;
    }

    /**
     * 获取机构添加页面和数据信息
     * @return
     */
    @GetMapping("getAdd")
    public String getAdd(@RequestParam(value = "id") Long id, ModelMap modelMap){
        SysOrgInfo sysOrgInfo = sysOrgInfoService.getOrgInfo(id);
        modelMap.put("fatherId", id);
        modelMap.put("fatherOrgName", sysOrgInfo.getOrgName());
        return "application/sys/org/org_add";
    }

    /**
     * 新增机构
     * @param orgSaveVO
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public YSDRZPResult add(@RequestBody OrgSaveVO orgSaveVO){
        YSDRZPResult ysdrzpResult = sysOrgInfoService.saveOrgInfo(orgSaveVO);
        return ysdrzpResult;
    }

}
