package com.sundy.common.constant;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月21日 11:21:37
 * 描述：常量
 */
public class Constant {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 2;

    /**
     * token有效期（分钟）
     */
    public static final int TOKEN_EXPIRES_MINUTES = 5;
    /**
     * 存放Token的header字段
     */
    public static final String DEFAULT_TOKEN_NAME = "Access-Token";

    public static final String SESSION_KEY = "session-key";
}
