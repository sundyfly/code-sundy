package com.sundy.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
    @NotBlank
    private String userName;

    /**
     * 密码
     */
    @Column(name = "user_pwd")
    @NotBlank
    @Length(max = 16,message = "密码不能大于16位数")
    @Length(min = 6 ,message = "密码不能小于6位数")
    private String userPwd;

    /**
     * 性别
     */
    @Column(name = "user_sex")
    @NotBlank
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
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$",message = "手机号码格式不正确")
    private String userPhone;

    /**
     * 状态
     */
    @Column(name = "user_state")
    @Pattern(regexp = "(-1|0|1)",message = "状态值只能是-1,0,1")
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