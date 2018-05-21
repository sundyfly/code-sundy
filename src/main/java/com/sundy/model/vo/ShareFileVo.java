package com.sundy.model.vo;

import com.sundy.model.entity.ShareFile;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年04月14日 15:36:54
 * 描述：
 */
public class ShareFileVo extends ShareFile{
    private String fileSizeStr;

    public String getFileSizeStr() {
        return fileSizeStr;
    }

    public ShareFileVo setFileSizeStr(String fileSizeStr) {
        this.fileSizeStr = fileSizeStr;
        return this;
    }
}
