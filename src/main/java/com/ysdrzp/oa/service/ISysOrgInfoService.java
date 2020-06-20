package com.ysdrzp.oa.service;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dto.result.OrgTreeDTO;
import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.vo.OrgSaveVO;
import com.ysdrzp.oa.vo.OrgUpdateVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 机构管理接口
 */
public interface ISysOrgInfoService extends IBaseService<SysOrgInfo>{

    /**
     * 获取机构信息
     * @param id
     * @return
     */
    SysOrgInfo getOrgInfo(Long id);

    /**
     * 根据机构名称查询机构信息
     * @param orgName
     * @return
     */
    SysOrgInfo selectByOrgName(String orgName);

    /**
     * 获取组织机构树
     * @return
     * @param id
     */
    List<HashMap<String, Object>> getOrgTree(@Param("id") Long id);

    /**
     * 获取机构详情
     * @param id
     * @return
     */
    OrgTreeDTO getOrgDetail(Long id);

    /**
     * 更新机构信息
     * @param orgUpdateVO
     * @return
     */
    YSDRZPResult updateOrgInfo(OrgUpdateVO orgUpdateVO);

    /**
     * 删除机构
     * @param id
     * @return
     */
    YSDRZPResult deleteOrgInfo(Long id);

    /**
     * 保存
     * @param orgSaveVO
     * @return
     */
    YSDRZPResult saveOrgInfo(OrgSaveVO orgSaveVO);
}
