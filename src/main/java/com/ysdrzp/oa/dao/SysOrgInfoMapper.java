package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.vo.OrgSearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOrgInfoMapper extends IBaseMapper<SysOrgInfo>{

    List<SysOrgInfo> getList(OrgSearchVO orgSearchVO);

    /**
     * 根据机构名称获取机构信息
     * @param orgName
     * @return
     */
    SysOrgInfo selectByOrgName(@Param("orgName") String orgName);
}