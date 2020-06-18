package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.entity.SysUser;
import com.ysdrzp.oa.vo.UsersSearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends IBaseMapper<SysUser>{

    /**
     * 获取用户列表
     * @param usersSearchVO
     * @return
     */
    List<SysUser> list(UsersSearchVO usersSearchVO);

    /**
     * 根据用户名称查询用户信息
     * @param userName
     * @return
     */
    SysUser selectByUserName(@Param("userName") String userName);
}