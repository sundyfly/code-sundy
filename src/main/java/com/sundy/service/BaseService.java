package com.sundy.service;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Service基础接口
 * @author sundy
 * @date 2018年03月10 11:15:25
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
     * @param primaryKey
     * @return
     */
    int deleteByPrimaryKey(Object primaryKey);

    /**
     * 根据转入的实体类插入数据库
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 根据转入的实体类选择性的插入数据库
     *
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     *根据主键查询实体类
     *
     * @param primaryKey
     * @return
     */
    T selectByPrimaryKey(Object primaryKey);

    /**
     * 查询实体类的所有数据
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据主键修改实体类
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 根据主键修改实体类,为NULL的不更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);
}
