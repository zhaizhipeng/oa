package com.ysdrzp.oa.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.MD5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.constant.YSDRZPConstant;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysUserMapper;
import com.ysdrzp.oa.dto.result.GenderDTO;
import com.ysdrzp.oa.dto.result.GenderDistributionDTO;
import com.ysdrzp.oa.dto.result.OrgDistributionDTO;
import com.ysdrzp.oa.dto.result.UserAuthSearchResult;
import com.ysdrzp.oa.entity.SysOrgInfo;
import com.ysdrzp.oa.entity.SysUser;
import com.ysdrzp.oa.entity.SysUserRole;
import com.ysdrzp.oa.service.ISysOrgInfoService;
import com.ysdrzp.oa.service.ISysUserRoleService;
import com.ysdrzp.oa.service.ISysUsersService;
import com.ysdrzp.oa.vo.UserAddVO;
import com.ysdrzp.oa.vo.UserAuthSearchVO;
import com.ysdrzp.oa.vo.UserRoleEditVO;
import com.ysdrzp.oa.vo.UsersSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ISysUserRoleService sysUserRoleService;

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
        sysUser.setOrgId(userAddVO.getOrgId());
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

    @Override
    public YSDRZPResult getGenderDistribution() {

        GenderDistributionDTO genderDistributionDTO = new GenderDistributionDTO();
        List<GenderDTO> genderDTOS = sysUserMapper.selectGenderTotal();
        if (CollectionUtil.isNotEmpty(genderDTOS)){
            for (GenderDTO genderDTO : genderDTOS){
                switch (genderDTO.getGenderType()){
                    case 0:
                        genderDistributionDTO.setSecret(genderDTO.getCount() == null ? 0 : genderDTO.getCount());
                        break;
                    case 1:
                        genderDistributionDTO.setMale(genderDTO.getCount() == null ? 0: genderDTO.getCount());
                        break;
                    case 2:
                        genderDistributionDTO.setFemale(genderDTO.getCount() == null ? 0: genderDTO.getCount());
                    default:
                        break;
                }
            }
        }
        return YSDRZPResult.ok(genderDistributionDTO);
    }

    @Override
    public YSDRZPResult getOrgDistribution() {
        List<Map<String, Object>> resultMapList = sysUserMapper.selectUserOrgTotal();
        OrgDistributionDTO orgDistributionDTO = new OrgDistributionDTO();
        if (CollectionUtil.isNotEmpty(resultMapList)){
            String[] orgNames = new String[resultMapList.size()];
            Long[] orgCounts = new Long[resultMapList.size()];
            for (int i = 0; i < resultMapList.size(); i++){
                orgNames[i] = String.valueOf(resultMapList.get(i).get("orgName"));
                orgCounts[i] = (Long) resultMapList.get(i).get("orgCounts");
            }
            orgDistributionDTO.setOrgNames(orgNames);
            orgDistributionDTO.setOrgCounts(orgCounts);
        }
        return YSDRZPResult.ok(orgDistributionDTO);
    }

    @Override
    public YSDRZPResult userAuthList(UserAuthSearchVO userAuthSearchVO) {
        PageHelper.startPage(userAuthSearchVO.getPage(), userAuthSearchVO.getLimit());

        List<UserAuthSearchResult> userAuthSearchResults = sysUserMapper.getUserAuthList(userAuthSearchVO);
        PageInfo<UserAuthSearchResult> pageInfo = new PageInfo<>(userAuthSearchResults);

        if (CollectionUtil.isNotEmpty(userAuthSearchResults)){
            for (UserAuthSearchResult userAuthSearchResult : userAuthSearchResults){
                List<String> list = sysUserMapper.getRoleNamesByUserId(userAuthSearchResult.getUserId());
                userAuthSearchResult.setRoleCnNames(list != null ? list.toArray() : null);
            }
        }
        return YSDRZPResult.ok("ok", pageInfo.getTotal(), userAuthSearchResults);
    }

    @Override
    public List<String> getRoleNamesByUserId(Long userId) {
        List<String> list = sysUserMapper.getRoleNamesByUserId(userId);
        return list;
    }

    @Override
    public YSDRZPResult editUserRole(UserRoleEditVO userRoleEditVO) {

        Long userId = userRoleEditVO.getUserId();

        sysUserMapper.delRoles(userRoleEditVO.getUserId());

        List<SysUserRole> sysUserRoles = new ArrayList<>();

        Long[] roles = userRoleEditVO.getRoleIds();
        if (roles.length > 0){
            for (int i = 0; i < roles.length; i++){
                Long roleId = roles[i];
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                sysUserRole.setStatus(1);
                sysUserRole.setCreateOperId(-1l);
                sysUserRole.setCreateOperName("管理员");
                sysUserRole.setCreateTime(DateUtil.date());
                sysUserRole.setUpdateOperId(-1l);
                sysUserRole.setUpdateOperName("管理员");
                sysUserRole.setUpdateTime(DateUtil.date());
                sysUserRoles.add(sysUserRole);
            }
        }

        sysUserRoleService.batchInsertUserRole(sysUserRoles);
        return YSDRZPResult.ok("用户角色编辑成功");
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        List<Long> roleIds = sysUserMapper.getRoleIdsByUserId(userId);
        return roleIds;
    }

}
