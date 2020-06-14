package com.ysdrzp.oa.service.impl;

import com.ysdrzp.oa.dao.IBaseMapper;
import com.ysdrzp.oa.entity.OrgInfo;
import com.ysdrzp.oa.service.IBaseService;
import org.springframework.stereotype.Service;

/**
 * 基础 service 实现类
 * @param <T>
 */
@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    public abstract IBaseMapper getMapper();

    @Override
    public int deleteByPrimaryKey(Long id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T t) {
        return getMapper().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getMapper().insertSelective(t);
    }

    @Override
    public OrgInfo selectByPrimaryKey(Long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return getMapper().updateByPrimaryKey(t);
    }
}
