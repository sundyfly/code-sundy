package com.sundy.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月16日 17:05:41
 * 描述：Web上下文工具类
 */
public class WebContextUtil {

    /**
     * 获取HTTP请求
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
}
