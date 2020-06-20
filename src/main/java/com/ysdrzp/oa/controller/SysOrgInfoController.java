package com.ysdrzp.oa.controller;

import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dto.result.OrgTreeDTO;
import com.ysdrzp.oa.service.ISysOrgInfoService;
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
     * 渲染机构详情
     * @return
     */
    @RequestMapping("detail")
    public String detail(@RequestParam("id") Long id, ModelMap modelMap){
        OrgTreeDTO orgTreeDTO = sysOrgInfoService.getOrgDetail(id);
        String parentOrgName = orgTreeDTO.getParent() == null ? "" : orgTreeDTO.getParent().getOrgName();

        modelMap.put("parentOrgName", parentOrgName);
        modelMap.put("orgDetail", orgTreeDTO);
        return "/sys/org/org_detail";
    }

    /**
     * 修改机构信息
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public YSDRZPResult update(@RequestBody OrgUpdateVO orgUpdateVO){
        System.out.println(JSONUtil.toJsonStr(orgUpdateVO));
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
        System.out.println(JSONUtil.toJsonStr(orgUpdateVO));
        YSDRZPResult ysdrzpResult = sysOrgInfoService.deleteOrgInfo(orgUpdateVO.getId());
        return ysdrzpResult;
    }

}
