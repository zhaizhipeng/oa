package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.dto.result.GenderDTO;
import com.ysdrzp.oa.entity.SysUser;
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
     * @param userName
     * @param mobilePhone
     * @return
     */
    SysUser selectUserIsExist(@Param("userName") String userName, @Param("mobilePhone") String mobilePhone);

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
}