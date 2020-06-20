package com.ysdrzp.oa.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysRoleMapper;
import com.ysdrzp.oa.entity.SysRole;
import com.ysdrzp.oa.service.ISysRoleService;
import com.ysdrzp.oa.vo.RoleAddVO;
import com.ysdrzp.oa.vo.RoleSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IBaseMapper getMapper() {
        return sysRoleMapper;
    }

    @Override
    public YSDRZPResult getList(RoleSearchVO roleSearchVO) {
        PageHelper.startPage(roleSearchVO.getPage(), roleSearchVO.getLimit());
        List<SysRole> roles = sysRoleMapper.getlist(roleSearchVO);
        PageInfo<SysRole> pageInfo = new PageInfo<>(roles);
        return YSDRZPResult.ok("ok", pageInfo.getTotal(), roles);
    }

    @Override
    public YSDRZPResult addRole(RoleAddVO roleAddVO) {

        SysRole sysRole = sysRoleMapper.selectRoleIsExist(roleAddVO.getRoleCnName());
        if (sysRole != null){
            return YSDRZPResult.error("角色已存在");
        }

        sysRole = new SysRole();
        sysRole.setRoleCnName(roleAddVO.getRoleCnName());
        sysRole.setRoleEnName(roleAddVO.getRoleEnName());
        sysRole.setMiscDesc(roleAddVO.getMiscDesc());
        sysRole.setCreateOperId(-1l);
        sysRole.setCreateTime(DateUtil.date());
        sysRole.setCreateOperName("系统管理员");
        sysRole.setUpdateOperId(-1l);
        sysRole.setUpdateTime(DateUtil.date());
        sysRole.setUpdateOperName("系统管理员");
        sysRoleMapper.insertSelective(sysRole);
        return YSDRZPResult.ok("添加角色成功");
    }

    @Override
    public YSDRZPResult enableRole(Long id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRole == null){
            return YSDRZPResult.ok("角色不存在");
        }
        
        if (sysRole != null){
            if (new Integer(1) == sysRole.getDisabled()){
                return YSDRZPResult.ok("角色已经是启用状态");
            }
            sysRole.setDisabled(new Integer(1));
            sysRole.setUpdateTime(DateUtil.date());
            sysRoleMapper.updateByPrimaryKey(sysRole);
        }
        return YSDRZPResult.ok("启用成功");
    }

    @Override
    public YSDRZPResult disableRole(Long id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRole == null){
            return YSDRZPResult.ok("角色不存在");
        }
        if (sysRole != null){
            if (new Integer(0) == sysRole.getDisabled()){
                return YSDRZPResult.ok("角色已经是禁用状态");
            }
            sysRole.setDisabled(new Integer(0));
            sysRole.setUpdateTime(DateUtil.date());
            sysRoleMapper.updateByPrimaryKey(sysRole);
        }
        return YSDRZPResult.ok("禁用成功");
    }

    @Override
    public YSDRZPResult delRole(Long id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRole == null){
            return YSDRZPResult.ok("角色不存在");
        }
        if (sysRole != null){
            // 执行逻辑删除
            sysRole.setStatus(new Integer(0));
            sysRole.setUpdateTime(DateUtil.date());
            sysRoleMapper.updateByPrimaryKey(sysRole);
        }
        return YSDRZPResult.ok("删除成功");
    }

}
