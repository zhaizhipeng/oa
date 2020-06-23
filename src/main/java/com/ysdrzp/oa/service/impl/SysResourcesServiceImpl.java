package com.ysdrzp.oa.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ysdrzp.oa.common.YSDRZPResult;
import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.dao.SysResourcesMapper;
import com.ysdrzp.oa.dto.result.MenuTreeDTO;
import com.ysdrzp.oa.entity.SysResources;
import com.ysdrzp.oa.entity.SysRoleResources;
import com.ysdrzp.oa.service.ISysResourcesService;
import com.ysdrzp.oa.service.ISysRoleResourcesService;
import com.ysdrzp.oa.vo.ResourcesSaveVO;
import com.ysdrzp.oa.vo.ResourcesUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysResourcesServiceImpl extends BaseServiceImpl<SysResources> implements ISysResourcesService {

    @Autowired
    private ISysRoleResourcesService sysRoleResourcesService;

    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Override
    public IBaseMapper getMapper() {
        return sysResourcesMapper;
    }

    @Override
    public SysResources getResources(Long id) {
        SysResources sysResources = sysResourcesMapper.selectByPrimaryKey(id);
        return sysResources;
    }

    @Override
    public SysResources selectByResourcesName(String resourcesName) {
        return sysResourcesMapper.selectByResourcesName(resourcesName);
    }

    @Override
    public List<HashMap<String, Object>> getMenuTree(Long id) {
        List<MenuTreeDTO> menuTreeDTOList = sysResourcesMapper.getMenuTree(id);

        List<HashMap<String, Object>> result = new ArrayList<>();
        result = buildMenuTree(menuTreeDTOList, result);
        return result;
    }

    /**
     * 生成资源树
     * @param menuTreeDTOList
     * @param result
     * @return
     */
    private List<HashMap<String, Object>> buildMenuTree(List<MenuTreeDTO> menuTreeDTOList, List<HashMap<String, Object>> result) {

        if (CollectionUtil.isNotEmpty(menuTreeDTOList)){
            for (MenuTreeDTO menuTreeDTO : menuTreeDTOList){
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", menuTreeDTO.getId());
                map.put("title", menuTreeDTO.getResourcesName());
                map.put("spread", true);

                List<HashMap<String, Object>> childrenList = new ArrayList<>();
                List<MenuTreeDTO> children = menuTreeDTO.getChildren();
                map.put("children", buildMenuTree(children, childrenList));
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public List<HashMap<String, Object>> getRoleMenuTree(Long roleId) {
        List<MenuTreeDTO> menuTreeDTOList = sysResourcesMapper.getMenuTree(null);

        Map<Long, Long> roleCurrentResourceMap = new HashMap<>();
        List<SysRoleResources> sysRoleResources = sysRoleResourcesService.getAllRoleResources(roleId);
        if (CollectionUtil.isNotEmpty(sysRoleResources)){
            for (int i = 0; i < sysRoleResources.size(); i++){
                roleCurrentResourceMap.put(sysRoleResources.get(i).getResourcesId(), sysRoleResources.get(i).getRoleId());
            }
        }

        System.out.println("roleCurrentResourceMap:" + JSONUtil.toJsonStr(roleCurrentResourceMap));

        Integer deep = 1;

        List<HashMap<String, Object>> result = new ArrayList<>();
        result = buildRoleMenuTree(menuTreeDTOList, result, roleCurrentResourceMap, deep);
        return result;
    }

    /**
     * 生成角色资源树-选中已有角色
     * @param menuTreeDTOList
     * @param result
     * @return
     */
    private List<HashMap<String, Object>> buildRoleMenuTree(List<MenuTreeDTO> menuTreeDTOList, List<HashMap<String, Object>> result,
                                                            Map<Long, Long> roleCurrentResourceMap, Integer deep) {

        if (CollectionUtil.isNotEmpty(menuTreeDTOList)){
            for (MenuTreeDTO menuTreeDTO : menuTreeDTOList){
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", menuTreeDTO.getId());
                map.put("title", menuTreeDTO.getResourcesName());
                map.put("spread", true);
                if (deep == 3 && roleCurrentResourceMap.get(menuTreeDTO.getId()) != null){
                    map.put("checked", true);
                }
                List<HashMap<String, Object>> childrenList = new ArrayList<>();
                List<MenuTreeDTO> children = menuTreeDTO.getChildren();

                map.put("children", buildRoleMenuTree(children, childrenList, roleCurrentResourceMap, deep + 1));
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public MenuTreeDTO getMenuDetail(Long id) {
        MenuTreeDTO menuTreeDTO = sysResourcesMapper.selectResourcesDetail(id);
        return menuTreeDTO;
    }

    @Override
    public YSDRZPResult updateResources(ResourcesUpdateVO resourcesUpdateVO) {

        SysResources sysResources = sysResourcesMapper.selectByPrimaryKey(resourcesUpdateVO.getId());
        if (sysResources == null){
            return YSDRZPResult.ok("菜单资源不存在");
        }
        sysResources.setResourcesName(resourcesUpdateVO.getResourcesName());
        sysResources.setUrlType(resourcesUpdateVO.getUrlType());
        sysResources.setMiscDesc(resourcesUpdateVO.getMiscDesc());
        sysResources.setUpdateTime(DateUtil.date());
        sysResourcesMapper.updateByPrimaryKeySelective(sysResources);
        return YSDRZPResult.ok("更新成功");
    }

    @Override
    public YSDRZPResult deleteResources(Long id) {
        SysResources sysResources = sysResourcesMapper.selectByPrimaryKey(id);
        if (sysResources == null){
            return YSDRZPResult.ok("菜单资源不存在");
        }
        List<SysResources> list = sysResourcesMapper.selectByFatherId(sysResources.getId());
        if (CollectionUtil.isNotEmpty(list)){
            for (SysResources sysResources1 : list){
                deleteByPrimaryKey(sysResources1.getId());
            }
        }
        sysResourcesMapper.deleteByPrimaryKey(id);
        return YSDRZPResult.ok("删除成功");
    }

    @Override
    public YSDRZPResult saveResources(ResourcesSaveVO resourcesSaveVO) {

        if (resourcesSaveVO.getFatherId() == null){
            return YSDRZPResult.ok("没有选择父菜单资源");
        }

        if (StrUtil.isBlank(resourcesSaveVO.getResourcesName())){
            return YSDRZPResult.ok("菜单资源名称不能为空");
        }

        SysResources sysFatherResources = sysResourcesMapper.selectByPrimaryKey(resourcesSaveVO.getFatherId());
        if (sysFatherResources == null){
            return YSDRZPResult.ok("父资源菜单不存在");
        }

        SysResources sysResources = new SysResources();
        sysResources.setFatherId(sysFatherResources.getId());
        sysResources.setResourcesName(resourcesSaveVO.getResourcesName());
        sysResources.setUrlType(resourcesSaveVO.getUrlType());
        sysResources.setResourcesKey(IdUtil.randomUUID());
        sysResources.setMiscDesc(resourcesSaveVO.getMiscDesc());
        sysResources.setCreateOperId(-1l);
        sysResources.setCreateTime(DateUtil.date());
        sysResources.setCreateOperName("管理员");
        sysResources.setUpdateOperId(-1l);
        sysResources.setUpdateTime(DateUtil.date());
        sysResources.setUpdateOperName("管理员");
        sysResourcesMapper.insertSelective(sysResources);
        return YSDRZPResult.ok("添加成功");
    }

}
