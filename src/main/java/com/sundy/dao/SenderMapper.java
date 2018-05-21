package com.sundy.dao;

import com.sundy.model.entity.Sender;
import com.sundy.model.entity.SenderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SenderMapper extends Mapper<Sender> {
    long countByEntity(SenderExample example);

    int deleteByEntity(SenderExample example);

    List<Sender> selectByEntity(SenderExample example);

    int updateByEntitySelective(@Param("record") Sender record, @Param("example") SenderExample example);

    int updateByEntity(@Param("record") Sender record, @Param("example") SenderExample example);
}