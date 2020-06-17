package com.ysdrzp.oa.service;

import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.Page;
import com.ysdrzp.oa.common.ResultUtil;
import com.ysdrzp.oa.entity.OrgInfo;
import com.ysdrzp.oa.vo.OrgSearchVo;

public interface IOrgInfoService extends IBaseService<OrgInfo>{

    /**
     * 分页查询组织机构列表
     * @param orgSearchVo
     * @return
     */
    ResultUtil getList(OrgSearchVo orgSearchVo);

    /**
     * 根据机构名称查询机构信息
     * @param orgName
     * @return
     */
    OrgInfo selectByOrgName(String orgName);
}
