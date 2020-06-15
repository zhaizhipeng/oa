package com.ysdrzp.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.Page;
import com.ysdrzp.oa.common.ResultUtil;
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
    public ResultUtil getList(Integer page, Integer limit, OrgSearchVo orgSearchVo) {
        PageHelper.startPage(1, 10);
        List<OrgInfo > list = orgInfoMapper.getList(orgSearchVo);
        PageInfo<OrgInfo> pageInfo = new PageInfo<>(list);

        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setMsg("ok");
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(pageInfo.getList());
        return resultUtil;
    }

    @Override
    public OrgInfo selectByOrgName(String orgName) {
        return null;
    }
}
