package com.sundy.service;

import com.github.pagehelper.PageInfo;
import com.sundy.model.entity.Sender;
import com.sundy.model.entity.User;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.List;


/**
 * Service测试
 * @author sundy
 * @date 2018年05月11 10:08 :41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class UserServiceTest extends BaseServiceTest{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);
    @Resource
    private UserService userService;
    @Resource
    SenderService senderService;
    @Override
    public void findEntityAll() {
//        PageInfo<User> entityAll = userService.findEntityAll();
        List<Sender> senders = senderService.selectAll();
        System.out.println("===============>"+senders);
    }

    @Override
    public void deleteByPrimaryKey() {
        userService.deleteByPrimaryKey(9);
    }

    @Override
    public void insert() {
        User u = new User();
        u.setUserName("jack love");
        u.setUserAddress("南宁");
        u.setUserPhone("15958588585");
        u.setUserPwd("8578422");
        u.setUserSex("男");
        u.setUserState("1");
        userService.insert(u);
    }

    @Override
    public void insertSelective() {
        User u = new User();
        u.setUserName("jack love 2");
        u.setUserAddress("");
        u.setUserPwd("8578422");
        u.setUserSex(null);
        userService.insertSelective(u);
    }

    @Override
    public void selectByPrimaryKey() {
        User user = userService.selectByPrimaryKey(123123);
        System.out.println(user);
    }

    @Override
    public void selectAll() {
        List<User> all = userService.selectAll();
        System.out.println("all=="+all);
    }

    @Override
    public void updateByPrimaryKey() {
        User u = new User();
        u.setId(24);
        u.setUserName("jack love");
        u.setUserAddress("南宁万秀");
        u.setUserPhone("15958588585");
        u.setUserPwd("8578422");
        u.setUserSex("男");
        u.setUserState("1");
        int i = userService.updateByPrimaryKey(u);
    }

    @Override
    public void updateByPrimaryKeySelective() {
        User u = new User();
        u.setId(24);
        u.setUserName("jack son");
        u.setUserAddress("");
        u.setUserPwd("8578422");
        u.setUserSex("女");
        u.setUserState(null);
        int i = userService.updateByPrimaryKeySelective(u);
        System.out.println(i);

    }
}
