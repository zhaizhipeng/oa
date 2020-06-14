package com.ysdrzp.oa.service;

import com.ysdrzp.oa.entity.OrgInfo;

/**
 * 基础 service
 * @param <T>
 */
public interface IBaseService<T> {

    int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    OrgInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

}
