package com.sundy.model.vo;

import com.sundy.model.entity.ShareFile;

/**
 * Created by tiptop on 2018/4/9.
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
