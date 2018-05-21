package com.sundy.service.impl;

import com.github.pagehelper.PageInfo;
import com.sundy.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月19日 10:41:58
 * 描述：BaseService的实现类
 */
public class BaseServiceImpl<T> implements BaseService<T>{

    @Autowired
    protected Mapper<T> mapper;

    @Override
    public PageInfo<T> findEntityAll() {
        return new PageInfo<>(mapper.selectAll());
    }

    @Override
    public int deleteByPrimaryKey(Object primaryKey) {
        return mapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(Object primaryKey) {
        return mapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

}
