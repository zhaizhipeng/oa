package com.ysdrzp.oa.service;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.vo.OrgSearchVO;

/**
 * 机构管理接口
 */
public interface ISysOrgInfoService extends IBaseService<SysOrgInfo>{

    /**
     * 分页查询组织机构列表
     * @param orgSearchVO
     * @return
     */
    YSDRZPResult getList(OrgSearchVO orgSearchVO);

}
