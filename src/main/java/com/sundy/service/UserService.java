package com.sundy.service;

import com.sundy.model.entity.User;

/**
 * Created by sundy on 2018/3/30.
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
