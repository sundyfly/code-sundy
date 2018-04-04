package com.sundy.controller;

import com.sundy.model.AjaxResult;
import com.sundy.model.entity.User;
import com.sundy.service.UserService;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by sundy on 2018/3/30.
 *
 */
@Api(description = "平台用户信息管理",tags = "UserController" ,basePath ="/users")
@RequestMapping(value = "/users")
@Controller
public class UserController extends BaseController<User>{
    private static final Logger LOGGER = org.apache.log4j.Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /* 如果在使用接口返回信息的时候，可以直接拿到BindingResult中的错误信息
       ,BindingResult 对象必须在 @Valid 的紧挨着的后面，否则接收不到错误信息。*/
    @ApiOperation(value = "新增User")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insert(@ApiParam(value = "新增User实体",required = true) @RequestBody @Valid User user, BindingResult result){
        return super.insert(userService,user,result);
    }

    @ApiOperation(value = "根据id物理删除指定的User，需谨慎！")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户ID", required = true, defaultValue = "", dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/{uid}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteByPrimaryKey(@PathVariable("uid") Integer uid){
        return super.deleteByPrimaryKey(userService,uid);
    }

    @ApiOperation(value = "根据id修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户ID", required = true, defaultValue = "", dataType = "int", paramType = "path"),
    })
    @RequestMapping(value = "/{uid}",method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult updateByPrimaryKeySelective(@PathVariable("uid") Integer uid,@ApiParam(value = "更新User详细信息", required = true) @RequestBody @Valid User user,BindingResult result){
        user.setId(uid);
        return super.updateByPrimaryKeySelective(userService,user,result);
    }

    @ApiOperation(value = "查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query" ,name = "pageNum",value = "当前页号" ,required = true ,dataType = "int"),
            @ApiImplicitParam(paramType = "query" ,name = "pageSize",value = "每页大小" ,required = true ,dataType = "int")
    })
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getEntityList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        return super.getEntityList(userService,pageNum,pageSize);
    }

    @ApiOperation(value = "根据用户ID查找用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",value = "用户ID",name = "uid",dataType = "int",required = true,defaultValue = "")
    })
    @RequestMapping(value = "/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getEntity(@PathVariable("uid")Integer uid){
        return super.getEntity(userService,uid);
    }

}
