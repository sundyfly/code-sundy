package com.sundy.common.annotation;

import java.lang.annotation.*;


/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年02月21日 10:28:57
 * 描述：自定义注解，标识是否忽略REST安全性检查
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSecurity {
    /**
     * 参观者
     */
    int VISITOR = 0;

    /**
     * 用户
     */
    int USER = 1;

    /**
     * 管理者
     */
    int ADMIN = 2;


    /**
     * 权限级别
     *
     * @return
     */
    int security () default VISITOR;

}
