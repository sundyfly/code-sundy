package com.sundy.dao;

import com.sundy.model.entity.ShareFile;
import com.sundy.model.entity.ShareFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ShareFileMapper extends Mapper<ShareFile> {
    long countByEntity(ShareFileExample example);

    int deleteByEntity(ShareFileExample example);

    List<ShareFile> selectByEntity(ShareFileExample example);

    int updateByEntitySelective(@Param("record") ShareFile record, @Param("example") ShareFileExample example);

    int updateByEntity(@Param("record") ShareFile record, @Param("example") ShareFileExample example);
}