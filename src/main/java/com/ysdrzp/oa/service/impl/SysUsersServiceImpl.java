package com.ysdrzp.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysUserMapper;
import com.ysdrzp.oa.entity.SysUser;
import com.ysdrzp.oa.service.ISysUsersService;
import com.ysdrzp.oa.vo.UsersSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUsersServiceImpl extends BaseServiceImpl<SysUser> implements ISysUsersService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IBaseMapper getMapper() {
        return sysUserMapper;
    }

    @Override
    public YSDRZPResult getList(UsersSearchVO usersSearchVO) {
        PageHelper.startPage(usersSearchVO.getPage(), usersSearchVO.getLimit());
        List<SysUser> users = sysUserMapper.list(usersSearchVO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
        return YSDRZPResult.ok("ok", pageInfo.getTotal(), users);
    }

}
