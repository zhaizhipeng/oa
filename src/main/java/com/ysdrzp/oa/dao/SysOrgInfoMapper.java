package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.dto.result.OrgTreeDTO;
import com.ysdrzp.oa.entity.SysOrgInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysOrgInfoMapper extends IBaseMapper<SysOrgInfo>{

    /**
     * 根据机构名称获取机构信息
     * @param orgName
     * @return
     */
    SysOrgInfo selectByOrgName(@Param("orgName") String orgName);

    /**
     * 获取机构树
     * @param id
     * @return
     */
    List<OrgTreeDTO> getOrgTree(Long id);

    /**
     * 获取机构详情
     * @param id
     * @return
     */
    OrgTreeDTO selectOrgDetail(Long id);

    /**
     * 获取父机构下的所有子机构
     * @param fatherOrgId
     * @return
     */
    List<SysOrgInfo> selectByFatherOrgId(@Param("fatherOrgId") String fatherOrgId);
}