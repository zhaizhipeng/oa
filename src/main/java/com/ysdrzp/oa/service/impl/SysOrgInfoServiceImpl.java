package com.ysdrzp.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysOrgInfoMapper;
import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.service.ISysOrgInfoService;
import com.ysdrzp.oa.vo.OrgSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOrgInfoServiceImpl extends BaseServiceImpl<SysOrgInfo> implements ISysOrgInfoService {

    @Autowired
    private SysOrgInfoMapper sysOrgInfoMapper;

    @Override
    public IBaseMapper getMapper() {
        return sysOrgInfoMapper;
    }

    @Override
    public YSDRZPResult getList(OrgSearchVo orgSearchVo) {
        PageHelper.startPage(orgSearchVo.getPage(), orgSearchVo.getLimit());
        List<SysOrgInfo> list = sysOrgInfoMapper.getList(orgSearchVo);
        PageInfo<SysOrgInfo> pageInfo = new PageInfo<>(list);

        YSDRZPResult result = new YSDRZPResult();
        result.setCode(0);
        result.setMsg("ok");
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }
}
