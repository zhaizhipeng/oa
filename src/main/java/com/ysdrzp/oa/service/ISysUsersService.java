package com.ysdrzp.oa.service;

import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.entity.SysUser;
import com.ysdrzp.oa.vo.UserAddVO;
import com.ysdrzp.oa.vo.UserAuthSearchVO;
import com.ysdrzp.oa.vo.UserRoleEditVO;
import com.ysdrzp.oa.vo.UsersSearchVO;

import java.util.List;

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

    /**
     * 获取用户角色列表
     * @param userAuthSearchVO
     * @return
     */
    YSDRZPResult userAuthList(UserAuthSearchVO userAuthSearchVO);

    /**
     * 获取用户拥有的角色名称列表
     * @param userId
     * @return
     */
    List<String> getRoleNamesByUserId(Long userId);

    /**
     * 用户角色授权
     * @param userRoleEditVO
     * @return
     */
    YSDRZPResult editUserRole(UserRoleEditVO userRoleEditVO);

    /**
     * 获取用户拥有的角色Id列表
     * @param userId
     * @return
     */
    List<Long> getRoleIdsByUserId(Long userId);

    /**
     * 用户登录
     * @param mobilePhone
     * @param password
     * @return
     */
    YSDRZPResult login(String mobilePhone, String password);
}
