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

import java.util.Date;
import java.util.List;

@Service
public class SysUsersServiceImpl extends BaseServiceImpl<SysUser> implements ISysUsersService {

    /**
     * 用户初始密码
     */
    public static final String USER_INITIAL_PASSWORD = "123456";

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
        List<SysUser> users = sysUserMapper.getlist(usersSearchVO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
        return YSDRZPResult.ok("ok", pageInfo.getTotal(), users);
    }

    @Override
    public YSDRZPResult addUser(UserAddVO userAddVO) {

        SysUser sysUser = sysUserMapper.selectUserIsExist(userAddVO.getUserName(), userAddVO.getMobilePhone());
        if (sysUser != null){
            return YSDRZPResult.error("用户已存在");
        }

        sysUser = new SysUser();
        sysUser.setMobilePhone(userAddVO.getMobilePhone());
        sysUser.setUserName(userAddVO.getUserName());
        sysUser.setMiscDesc(userAddVO.getMiscDesc());
        sysUser.setPassword(MD5.create().digest(USER_INITIAL_PASSWORD).toString());
        sysUser.setPwdValidDate(DateUtil.nextMonth());
        sysUser.setGender(userAddVO.getGender());
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

    @Override
    public YSDRZPResult enableUser(Long id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null){
            return YSDRZPResult.ok("用户不存在");
        }
        if (sysUser != null){
            if (new Integer(1) == sysUser.getDisabled()){
                return YSDRZPResult.ok("用户已经是启用状态");
            }
            sysUser.setDisabled(new Integer(1));
            sysUser.setUpdateTime(DateUtil.date());
            sysUserMapper.updateByPrimaryKey(sysUser);
        }
        return YSDRZPResult.ok("启用成功");
    }

    @Override
    public YSDRZPResult disableUser(Long id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null){
            return YSDRZPResult.ok("用户不存在");
        }
        if (sysUser != null){
            if (new Integer(0) == sysUser.getDisabled()){
                return YSDRZPResult.ok("用户已经是禁用状态");
            }
            sysUser.setDisabled(new Integer(0));
            sysUser.setUpdateTime(DateUtil.date());
            sysUserMapper.updateByPrimaryKey(sysUser);
        }
        return YSDRZPResult.ok("禁用成功");
    }

    @Override
    public YSDRZPResult delUser(Long id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null){
            return YSDRZPResult.ok("用户不存在");
        }
        if (sysUser != null){
            // 执行逻辑删除
            sysUser.setStatus(new Integer(0));
            sysUser.setUpdateTime(DateUtil.date());
            sysUserMapper.updateByPrimaryKey(sysUser);
        }
        return YSDRZPResult.ok("删除成功");
    }

    @Override
    public YSDRZPResult pwdReset(Long id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null){
            return YSDRZPResult.ok("用户不存在");
        }
        if (sysUser != null){
            sysUser.setPassword(MD5.create().digest(USER_INITIAL_PASSWORD).toString());
            sysUser.setPwdValidDate(DateUtil.nextMonth());
            sysUser.setUpdateTime(DateUtil.date());
            sysUserMapper.updateByPrimaryKey(sysUser);
        }
        return YSDRZPResult.ok("密码重置成功");
    }

}
