package com.sundy.service.impl;

import com.github.pagehelper.Page;
import com.sundy.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author sundy
 * @date 2018年03月19日 10:41
 */
public class BaseServiceImpl<T> implements BaseService<T>{

    @Autowired
    protected Mapper<T> mapper;

    @Override
    public Page<T> findEntityAll() {
        return (Page<T>) mapper.selectAll();
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
