package com.sundy.common.annotation;

import java.lang.annotation.*;


/**
 * 自定义注解，标识是否忽略REST安全性检查
 * @author sundy
 * @date 2017年10月19日 10:41
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
