package com.sundy.controller;

import com.sundy.authorization.manager.TokenManager;
import com.sundy.authorization.model.TokenModel;
import com.sundy.common.annotation.IgnoreSecurity;
import com.sundy.common.constant.Constant;
import com.sundy.common.constant.StatusCode;
import com.sundy.common.util.Base64Utils;
import com.sundy.common.util.ExceptionUtils;
import com.sundy.model.AjaxResult;
import com.sundy.model.entity.User;
import com.sundy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 平台登录、注销
 * @author sundy
 * @date 2018年03月30 15:37:25
 */
@Api(description = "平台登录注册", tags = "HomeController" ,basePath = "/home")
@RequestMapping("/home")
@Controller
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录" ,notes = "用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query" ,name = "userName",value = "用户名" ,required = true ,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name = "password",value = "用户密码" ,required = true ,dataType = "String")
    })
    @IgnoreSecurity
    @ResponseBody
    public AjaxResult login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, HttpServletRequest request){
        AjaxResult result = new AjaxResult();

        try{
            User user = userService.findUser(userName,password);
            if(user == null){
                result.setCode(StatusCode.LOGIN_FAILURE);
                result.setMsg("登录失败，用户名或者密码错误");
            }else {
                TokenModel token;
                // 判断用户是否已经登录过，如果登录过，就将redis缓存中的token删除，重新创建新的token值，保证一个用户在一个时间段只有一个可用 Token
                if (tokenManager.hasToken(user.getId())) {
                    //清除过时的token
                    tokenManager.deleteToken(user.getId());
                    //创建token
                    token = tokenManager.createToken(user.getId());
                } else {
                    //创建token
                    token = tokenManager.createToken(user.getId());
                }
                request.getSession().setAttribute(Constant.DEFAULT_TOKEN_NAME, Base64Utils.encodeData(token.getToken()));
                result.setData(user);
            }
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            String msg = "用户登录失败！参数信息：userName = " + userName + ",password = " + password;
            LOGGER.error(msg+"\n"+ ExceptionUtils.getException(e));
            result.setMsg(msg);
        }
        return result;
    }

    @ApiOperation(value = "用户注销" ,notes = "用户注销")
    @ApiImplicitParam(paramType = "query" ,name = "uid" ,value = "用户ID",dataType = "int")
    @RequestMapping(value = "/logout" ,method = RequestMethod.POST)
    @ResponseBody
    @IgnoreSecurity(security = IgnoreSecurity.USER)
    public AjaxResult logout(@RequestParam("uid")int uid,HttpServletRequest request){
        AjaxResult result = new AjaxResult();
        try{
            if(tokenManager.hasToken(uid)){
                request.getSession().removeAttribute(Constant.DEFAULT_TOKEN_NAME);
                tokenManager.deleteToken(uid);
            }else {
                result.setCode(StatusCode.REQUEST_ERROR);
                result.setMsg("该用户"+uid+"并未登录");
            }
        }catch(Exception e){
            result.setCode(StatusCode.REQUEST_ERROR);
            result.setMsg("logout failure.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }


}
