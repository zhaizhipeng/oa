package com.ysdrzp.oa.service.impl;

import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.OrgInfoMapper;
import com.ysdrzp.oa.entity.OrgInfo;
import com.ysdrzp.oa.service.IOrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgInfoServiceImpl extends BaseServiceImpl<OrgInfo> implements IOrgInfoService {

    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @Override
    public IBaseMapper getMapper() {
        return orgInfoMapper;
    }
}
