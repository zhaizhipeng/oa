package com.ysdrzp.oa.dao;

import com.ysdrzp.oa.entity.OrgInfo;

/**
 * 基础 Dao
 * @param <T>
 */
public interface IBaseMapper<T> {

    int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    OrgInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
}
