package com.sundy.service;

import com.sundy.model.entity.User;

/**
 * @author sundy
 * @date 2018年04月10日 15:11
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
