package com.sundy.dao;

import com.sundy.model.entity.User;
import com.sundy.model.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    long countByEntity(UserExample example);

    int deleteByEntity(UserExample example);

    List<User> selectByEntity(UserExample example);

    int updateByEntitySelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByEntity(@Param("record") User record, @Param("example") UserExample example);
}