package com.ysdrzp.oa.service.impl;

import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysUserRoleMapper;
import com.ysdrzp.oa.entity.SysUserRole;
import com.ysdrzp.oa.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IBaseMapper getMapper() {
        return sysUserRoleMapper;
    }

    @Override
    public int batchInsertUserRole(List<SysUserRole> userRoleList) {
        int count = sysUserRoleMapper.batchInsert(userRoleList);
        return count;
    }

}
