package com.sundy.service;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by tiptop on 2018/4/3.
 */
public interface BaseService<T> {

    /**
     * 数据库对应表的实体类分页方法
     *
     * @return
     */
    Page<T> findEntityAll();

    /**
     *根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据转入的实体类插入数据库
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 根据转入的实体类选择性的插入数据库
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(Integer id);

    List<T> selectAll();

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);
}
