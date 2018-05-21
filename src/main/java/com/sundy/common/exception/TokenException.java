package com.sundy.common.exception;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月01日 10:32:24
 * 描述：Token过期时抛出异常
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;

    public TokenException(String msg) {
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}