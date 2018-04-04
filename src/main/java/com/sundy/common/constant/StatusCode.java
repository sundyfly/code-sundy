package com.sundy.common.constant;

/**
 * 业务状态码
 * @author sundy
 * @date 2017年10月19日 10:41
 */
public interface StatusCode {
    /**
     * 请求成功
     */
    public static final int REQUEST_SUCCESS = 200;

    /**
     * 登录失败，用户名或者密码错误
     */
    public static final int LOGIN_FAILURE = 201;

    /**
     * Bad Request
     */
    public static final int REQUEST_ERROR = 400;

    /**
     * Token已失效
     */
    public static final int TOKEN_INVALID = 401;

    /**
     * Unsupported Media Type(内容类型不支持)
     */
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;

    /**
     * 内部服务器错误
     */
    public static final int SYSTEM_EXCEPTION = 500;


}
