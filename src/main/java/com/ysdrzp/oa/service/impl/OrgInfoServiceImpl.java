package com.ysdrzp.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.Page;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.OrgInfoMapper;
import com.ysdrzp.oa.entity.OrgInfo;
import com.ysdrzp.oa.service.IOrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgInfoServiceImpl extends BaseServiceImpl<OrgInfo> implements IOrgInfoService {

    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @Override
    public IBaseMapper getMapper() {
        return orgInfoMapper;
    }

    @Override
    public PageInfo<OrgInfo> getList(Page page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        List<OrgInfo > list = orgInfoMapper.getList();
        PageInfo<OrgInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public OrgInfo selectByOrgName(String orgName) {
        return null;
    }
}
