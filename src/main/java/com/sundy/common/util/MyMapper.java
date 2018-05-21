package com.sundy.common.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月14日 16:46:14
 * 描述：继承通用的Mapper，方便后期自己的扩展
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
