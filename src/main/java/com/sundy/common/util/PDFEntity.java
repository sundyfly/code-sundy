package com.sundy.common.util;

import java.util.Arrays;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年05月18日 16:26:12
 * 描述：
 */
public class PDFEntity {
    private float version; //pdf的版本号
    private int totalPageSize; //pdf的页数
    private String text; //pdf的文本内容
    private String[] content; //dpf的每页的内容

    public PDFEntity() {
        // TODO Auto-generated constructor stub
    }

    public PDFEntity(float version, int totalPageSize, String txet,
                     String[] content) {
        this.version = version;
        this.totalPageSize = totalPageSize;
        this.text = txet;
        this.content = content;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public int getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public String getTxet() {
        return text;
    }

    public void setTxet(String txet) {
        this.text = txet;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "version = " + version + " , totalPageSize = "
                + totalPageSize + " , txet = " + text + " , content = "
                + Arrays.toString(content) + " ]";
    }
}
