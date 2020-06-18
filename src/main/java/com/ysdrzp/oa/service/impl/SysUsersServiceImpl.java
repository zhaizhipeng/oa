package com.ysdrzp.oa.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.MD5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.constant.YSDRZPConstant;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysOrgInfoMapper;
import com.ysdrzp.oa.dao.SysUserMapper;
import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.entity.SysUser;
import com.ysdrzp.oa.service.ISysOrgInfoService;
import com.ysdrzp.oa.service.ISysUsersService;
import com.ysdrzp.oa.vo.UserAddVO;
import com.ysdrzp.oa.vo.UsersSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUsersServiceImpl extends BaseServiceImpl<SysUser> implements ISysUsersService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysOrgInfoService sysOrgInfoService;

    @Override
    public IBaseMapper getMapper() {
        return sysUserMapper;
    }

    @Override
    public YSDRZPResult getList(UsersSearchVO usersSearchVO) {
        if (usersSearchVO.getOrgName() != null){
            SysOrgInfo sysOrgInfo = sysOrgInfoService.selectByOrgName(usersSearchVO.getOrgName());
            if (sysOrgInfo == null){
                return YSDRZPResult.ok(YSDRZPConstant.RETURN_SUCCESS_MSG, 0l, null);
            }
            usersSearchVO.setOrgId(sysOrgInfo.getOrgId());
        }
        PageHelper.startPage(usersSearchVO.getPage(), usersSearchVO.getLimit());
        List<SysUser> users = sysUserMapper.list(usersSearchVO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
        return YSDRZPResult.ok("ok", pageInfo.getTotal(), users);
    }

    @Override
    public YSDRZPResult addUser(UserAddVO userAddVO) {

        SysUser sysUser = sysUserMapper.selectByUserName(userAddVO.getUserName());
        if (sysUser != null){
            return YSDRZPResult.error("用户已存在");
        }

        sysUser = new SysUser();
        sysUser.setMobilePhone(userAddVO.getMobilePhone());
        sysUser.setUserName(userAddVO.getUserName());
        sysUser.setMiscDesc(userAddVO.getMiscDesc());
        /** 初始密码为123456 */
        sysUser.setPassword(MD5.create().digest("123456").toString());
        sysUser.setPwdValidDate(DateUtil.nextMonth());
        sysUser.setOrgId("00");
        sysUser.setCreateOperId(-1l);
        sysUser.setCreateTime(DateUtil.date());
        sysUser.setCreateOperName("系统管理员");
        sysUser.setUpdateOperId(-1l);
        sysUser.setUpdateTime(DateUtil.date());
        sysUser.setUpdateOperName("系统管理员");
        sysUserMapper.insertSelective(sysUser);
        return YSDRZPResult.ok("添加用户成功");
    }

}
