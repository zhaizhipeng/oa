package com.ysdrzp.oa.service;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.entity.SysUser;
import com.ysdrzp.oa.vo.UserAddVO;
import com.ysdrzp.oa.vo.UsersSearchVO;

/**
 * 用户管理接口
 */
public interface ISysUsersService extends IBaseService<SysUser>{

    /**
     * 分页查询用户列表
     * @param usersSearchVO
     * @return
     */
    YSDRZPResult getList(UsersSearchVO usersSearchVO);

    /**
     * 新增用户
     * @param userAddVO
     * @return
     */
    YSDRZPResult addUser(UserAddVO userAddVO);

}
