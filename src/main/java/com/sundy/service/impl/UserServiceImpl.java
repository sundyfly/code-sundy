package com.sundy.service.impl;

import com.sundy.dao.UserMapper;
import com.sundy.model.entity.User;
import com.sundy.model.entity.UserExample;
import com.sundy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sundy on 2018/3/30.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(String userName, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName).andUserPwdEqualTo(password);
        List<User> list = userMapper.selectByEntity(example);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }
}
