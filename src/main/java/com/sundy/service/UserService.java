package com.sundy.service;

import com.sundy.model.entity.User;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年04月17日 17:58:29
 * 描述：
 */
public interface UserService extends BaseService<User>{

    /**
     * 根据用户名和密码查找用户
     *
     * @param userName
     * @param password
     * @return
     */
    User findUser(String userName, String password);

}
