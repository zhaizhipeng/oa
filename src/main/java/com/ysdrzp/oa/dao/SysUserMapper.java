package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.dto.result.GenderDTO;
import com.ysdrzp.oa.dto.result.UserAuthSearchResult;
import com.ysdrzp.oa.entity.SysUser;
import com.ysdrzp.oa.vo.UserAuthSearchVO;
import com.ysdrzp.oa.vo.UsersSearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends IBaseMapper<SysUser>{

    /**
     * 获取用户列表
     * @param usersSearchVO
     * @return
     */
    List<SysUser> getlist(UsersSearchVO usersSearchVO);

    /**
     * 根据用户名称查询用户信息
     * @param mobilePhone
     * @return
     */
    SysUser selectUserIsExist(@Param("mobilePhone") String mobilePhone);

    /**
     * 获取用户不同性别下的个数
     * @return
     */
    List<GenderDTO> selectGenderTotal();

    /**
     * 获取用户机构分布信息
     * @return
     */
    List<Map<String, Object>> selectUserOrgTotal();

    /**
     * 获取用户角色列表
     * @param userAuthSearchVO
     * @return
     */
    List<UserAuthSearchResult> getUserAuthList(UserAuthSearchVO userAuthSearchVO);

    /**
     * 获取用户角色名称
     * @param userId
     * @return
     */
    List<String> getRoleNamesByUserId(@Param("userId") Long userId);

    /**
     * 获取用户角色Id列表
     * @param userId
     * @return
     */
    List<Long> getRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * 删除用户角色
     * @param userId
     */
    void delRoles(Long userId);
}