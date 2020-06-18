package com.ysdrzp.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.OrgInfoMapper;
import com.ysdrzp.oa.entity.OrgInfo;
import com.ysdrzp.oa.service.IOrgInfoService;
import com.ysdrzp.oa.vo.OrgSearchVo;
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
    public YSDRZPResult getList(OrgSearchVo orgSearchVo) {
        PageHelper.startPage(orgSearchVo.getPage(), orgSearchVo.getLimit());
        List<OrgInfo > list = orgInfoMapper.getList(orgSearchVo);
        PageInfo<OrgInfo> pageInfo = new PageInfo<>(list);

        YSDRZPResult result = new YSDRZPResult();
        result.setCode(0);
        result.setMsg("ok");
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

    @Override
    public OrgInfo selectByOrgName(String orgName) {
        return null;
    }
}
