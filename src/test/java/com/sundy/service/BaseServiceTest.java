package com.sundy.service;

import org.junit.Test;

/**
 * BaseService测试抽象类
 * @author sundy
 * @date 2018年05月11 10:08 :41
 */
public abstract class BaseServiceTest{

    /**
     * 数据库对应表的实体类分页方法
     */
    @Test
    public abstract void findEntityAll();

    /**
     *根据主键删除
     */
    @Test
    public abstract void deleteByPrimaryKey();

    /**
     * 根据转入的实体类插入数据库
     */
    @Test
    public abstract void insert();

    /**
     * 根据转入的实体类选择性的插入数据库
     */
    @Test
    public abstract void insertSelective();

    /**
     *根据主键查询实体类
     */
    @Test
    public abstract void selectByPrimaryKey();

    /**
     * 查询实体类的所有数据
     */
    @Test
    public abstract void selectAll();

    /**
     * 根据主键修改实体类
     */
    @Test
    public abstract void updateByPrimaryKey();

    /**
     * 根据主键修改实体类,为NULL的不更新
     */
    @Test
    public abstract void updateByPrimaryKeySelective();

}
