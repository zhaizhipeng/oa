package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.vo.OrgSearchVo;
import java.util.List;

public interface SysOrgInfoMapper extends IBaseMapper<SysOrgInfo>{

    List<SysOrgInfo> getList(OrgSearchVo orgSearchVo);
}