package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.entity.OrgInfo;

import java.util.List;

public interface OrgInfoMapper extends IBaseMapper<OrgInfo> {

    List<OrgInfo> getList();
}