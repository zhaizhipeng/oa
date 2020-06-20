package com.ysdrzp.oa.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysOrgInfoMapper;
import com.ysdrzp.oa.dto.result.OrgTreeDTO;
import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.service.ISysOrgInfoService;
import com.ysdrzp.oa.vo.OrgUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    public SysOrgInfo selectByOrgName(String orgName) {
        return sysOrgInfoMapper.selectByOrgName(orgName);
    }

    @Override
    public List<HashMap<String, Object>> getOrgTree(Long id) {
        List<OrgTreeDTO> orgTreeList = sysOrgInfoMapper.getOrgTree(id);
        System.out.println(JSONUtil.toJsonStr(orgTreeList));

        List<HashMap<String, Object>> result = new ArrayList<>();
        result = buildOrgTree(orgTreeList, result);
        return result;
    }

    /**
     * 生成机构树
     * @param orgTreeList
     * @param result
     * @return
     */
    private List<HashMap<String, Object>> buildOrgTree(List<OrgTreeDTO> orgTreeList, List<HashMap<String, Object>> result) {

        if (CollectionUtil.isNotEmpty(orgTreeList)){
            for (OrgTreeDTO orgTreeDTO : orgTreeList){
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", orgTreeDTO.getId());
                map.put("title", orgTreeDTO.getOrgName());
                map.put("spread", true);

                List<HashMap<String, Object>> childrenList = new ArrayList<>();
                List<OrgTreeDTO> children = orgTreeDTO.getChildren();
                map.put("children", buildOrgTree(children, childrenList));
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public OrgTreeDTO getOrgDetail(Long id) {
        OrgTreeDTO orgTreeDTO = sysOrgInfoMapper.selectOrgDetail(id);
        return orgTreeDTO;
    }

    @Override
    public YSDRZPResult updateOrgInfo(OrgUpdateVO orgUpdateVO) {

        SysOrgInfo sysOrgInfo = sysOrgInfoMapper.selectByPrimaryKey(orgUpdateVO.getId());
        if (sysOrgInfo == null){
            return YSDRZPResult.ok("机构不存在");
        }
        sysOrgInfo.setOrgName(orgUpdateVO.getOrgName());
        sysOrgInfo.setSubName(orgUpdateVO.getSubName());
        sysOrgInfo.setUpdateTime(DateUtil.date());
        sysOrgInfoMapper.updateByPrimaryKeySelective(sysOrgInfo);
        return YSDRZPResult.ok("更新成功");
    }

    @Override
    public YSDRZPResult deleteOrgInfo(Long id) {
        SysOrgInfo sysOrgInfo = sysOrgInfoMapper.selectByPrimaryKey(id);
        if (sysOrgInfo == null){
            return YSDRZPResult.ok("机构不存在");
        }
        if (sysOrgInfo.getOrgId().equals("00")){
            return YSDRZPResult.ok("根节点不能删除");
        }
        List<SysOrgInfo> list = sysOrgInfoMapper.selectByFatherOrgId(sysOrgInfo.getOrgId());
        if (CollectionUtil.isNotEmpty(list)){
            for (SysOrgInfo sysOrgInfo1 : list){
                deleteByPrimaryKey(sysOrgInfo1.getId());
            }
        }
        sysOrgInfoMapper.deleteByPrimaryKey(id);
        return YSDRZPResult.ok("删除成功");
    }

}
