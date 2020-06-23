package com.ysdrzp.oa.service.impl;

import cn.hutool.core.date.DateUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysRoleResourcesMapper;
import com.ysdrzp.oa.entity.SysRoleResources;
import com.ysdrzp.oa.service.ISysRoleResourcesService;
import com.ysdrzp.oa.vo.RoleResourceEditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleResourcesServiceImpl extends BaseServiceImpl<SysRoleResources> implements ISysRoleResourcesService {

    @Autowired
    private SysRoleResourcesMapper sysRoleResourcesMapper;

    @Override
    public IBaseMapper getMapper() {
        return sysRoleResourcesMapper;
    }

    @Override
    public List<SysRoleResources> getAllRoleResources(Long roleId) {
        return sysRoleResourcesMapper.selectByRoleId(roleId);
    }

    @Override
    public YSDRZPResult editRoleResource(RoleResourceEditVO roleResourceEditVO) {

        sysRoleResourcesMapper.deleteByRoleId(roleResourceEditVO.getRoleId());

        String[] resourcesIds = roleResourceEditVO.getResourcesIds().split(",");

        List<SysRoleResources> sysRoleResourcesList = new ArrayList<>();

        if (resourcesIds.length > 0){
            for (int i = 0; i < resourcesIds.length; i++){
                String resourcesId = resourcesIds[i];
                SysRoleResources sysRoleResources = new SysRoleResources();
                sysRoleResources.setRoleId(roleResourceEditVO.getRoleId());
                sysRoleResources.setResourcesId(Long.parseLong(resourcesId));
                sysRoleResources.setStatus(1);
                sysRoleResources.setCreateOperId(-1l);
                sysRoleResources.setCreateOperName("管理员");
                sysRoleResources.setCreateTime(DateUtil.date());
                sysRoleResources.setUpdateOperId(-1l);
                sysRoleResources.setUpdateOperName("管理员");
                sysRoleResources.setUpdateTime(DateUtil.date());
                sysRoleResourcesList.add(sysRoleResources);
            }
        }

        sysRoleResourcesMapper.batchInsertRoleResources(sysRoleResourcesList);
        return YSDRZPResult.ok("角色授权成功");
    }


}
