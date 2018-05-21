package com.sundy.model;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年04月18日 9:25:56
 * 描述：分页返回的接口
 */
public class PageAjaxResult {

    /**
     * 业务自定义状态码
     */
    private Integer code = 0;

    /**
     * 返回信息
     */
    private String msg = "Request Success！";

    private int count;

    /**
     * 数据集
     */
    private Object data = null;

    public Integer getCode() {
        return code;
    }

    public PageAjaxResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public PageAjaxResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCount() {
        return count;
    }

    public PageAjaxResult setCount(int count) {
        this.count = count;
        return this;
    }

    public Object getData() {
        return data;
    }

    public PageAjaxResult setData(Object data) {
        this.data = data;
        return this;
    }
}
