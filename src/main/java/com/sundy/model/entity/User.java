package com.sundy.model.entity;

import javax.persistence.*;

@Table(name = "tb_user")
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "user_pwd")
    private String userPwd;

    /**
     * 性别
     */
    @Column(name = "user_sex")
    private String userSex;

    /**
     * 地址
     */
    @Column(name = "user_address")
    private String userAddress;

    /**
     * 电话
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 状态
     */
    @Column(name = "user_state")
    private String userState;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return user_pwd - 密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置密码
     *
     * @param userPwd 密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 获取性别
     *
     * @return user_sex - 性别
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * 设置性别
     *
     * @param userSex 性别
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * 获取地址
     *
     * @return user_address - 地址
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 设置地址
     *
     * @param userAddress 地址
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * 获取电话
     *
     * @return user_phone - 电话
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置电话
     *
     * @param userPhone 电话
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 获取状态
     *
     * @return user_state - 状态
     */
    public String getUserState() {
        return userState;
    }

    /**
     * 设置状态
     *
     * @param userState 状态
     */
    public void setUserState(String userState) {
        this.userState = userState;
    }
}