package com.ysdrzp.oa.service;

import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.Page;
import com.ysdrzp.oa.entity.OrgInfo;

public interface IOrgInfoService extends IBaseService<OrgInfo>{

    /**
     * 分页查询组织机构列表
     * @param page
     * @return
     */
    PageInfo<OrgInfo> getList(Page page);

    /**
     * 根据机构名称查询机构信息
     * @param orgName
     * @return
     */
    OrgInfo selectByOrgName(String orgName);
}
