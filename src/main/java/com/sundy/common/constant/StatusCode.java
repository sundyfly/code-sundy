package com.sundy.common.constant;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月01日 09:31:54
 * 描述：业务状态码
 */
public interface StatusCode {
    /**
     * 请求成功
     */
    int REQUEST_SUCCESS = 200;

    /**
     * 登录失败，用户名或者密码错误
     */
    int LOGIN_FAILURE = 201;

    /**
     * Bad Request
     */
    int REQUEST_ERROR = 400;

    /**
     * Token已失效
     */
    int TOKEN_INVALID = 401;

    /**
     * Unsupported Media Type(内容类型不支持)
     */
    int UNSUPPORTED_MEDIA_TYPE = 415;

    /**
     * 内部服务器错误
     */
    int SYSTEM_EXCEPTION = 500;


}
