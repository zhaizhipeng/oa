package com.ysdrzp.oa.controller;

import com.ysdrzp.oa.common.ResultUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.entity.OrgInfo;
import com.ysdrzp.oa.service.IOrgInfoService;
import com.ysdrzp.oa.vo.OrgSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResultUtil list(@RequestBody OrgSearchVo orgSearchVo){

        ResultUtil resultUtil = orgInfoService.getList(orgSearchVo);
        return resultUtil;
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
