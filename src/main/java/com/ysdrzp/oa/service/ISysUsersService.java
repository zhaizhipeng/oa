package com.ysdrzp.oa.service;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.constant.YSDRZPConstant;
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

    /**
     * 启用用户
     * @param id
     * @return
     */
    YSDRZPResult enableUser(Long id);

    /**
     * 禁用用户
     * @param id
     * @return
     */
    YSDRZPResult disableUser(Long id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    YSDRZPResult delUser(Long id);

    /**
     * 密码重置
     * @param id
     * @return
     */
    YSDRZPResult pwdReset(Long id);

    /**
     * 获取用户性别统计数据
     * @return
     */
    YSDRZPResult getGenderDistribution();

    /**
     * 获取用户机构数据
     * @return
     */
    YSDRZPResult getOrgDistribution();
}
