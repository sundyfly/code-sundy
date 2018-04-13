package com.sundy.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sundy.common.constant.StatusCode;
import com.sundy.common.util.ExceptionUtils;
import com.sundy.model.AjaxResult;
import com.sundy.model.PageAjaxResult;
import com.sundy.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 控制类基类，适用于对单张数据表的增、删、改、查操作
 * @author sundy
 * @date 2018年03月27 11:17:12
 */
public class BaseController<T> {

    private static final Logger LOGGER = org.apache.log4j.Logger.getLogger(BaseController.class);

    /**
     * 新增实体
     *
     * @param service
     * @param t
     * @param br
     * @return
     */
    protected AjaxResult insert(BaseService<T> service ,T t,BindingResult br){
        AjaxResult result = new AjaxResult();
        if (checkResult(br, result)){
            return result;
        }
        try{
            service.insert(t);
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("getEntityList.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }

    /**
     * 实体分页方法
     *
     * @param service
     * @param pageNum
     * @param pageSize
     * @return
     */
    protected AjaxResult getEntityList(BaseService<T> service, Integer pageNum, Integer pageSize){
        AjaxResult result = new AjaxResult();
        try{
            PageHelper.startPage(pageNum, pageSize);
            Page<T> page = service.findEntityAll();
            result.setData(page);
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("getEntityList.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }

    /**
     * 实体分页方法
     *
     * @param service
     * @param pageNum
     * @param pageSize
     * @return
     */
    protected PageAjaxResult getEntityLists(BaseService<T> service, Integer pageNum, Integer pageSize){
        PageAjaxResult result = new PageAjaxResult();
        try{
            PageHelper.startPage(pageNum, pageSize);
            Page<T> page = service.findEntityAll();
            result.setData(page);
            result.setCode(0);
            result.setCount(page.size());
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("getEntityLists.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }

    /**
     * 根据主键查询实体
     *
     * @param service
     * @param primaryKey
     * @return
     */
    protected AjaxResult getEntity(BaseService<T> service,Object primaryKey){
        AjaxResult result = new AjaxResult();
        try{
            T t = service.selectByPrimaryKey(primaryKey);
            result.setData(t);
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("getEntity.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }

    /**
     * 根据主键删除
     *
     * @param service
     * @param primaryKey
     * @return
     */
    protected AjaxResult deleteByPrimaryKey(BaseService<T> service, Object primaryKey) {
        AjaxResult result = new AjaxResult();
        try{
            service.deleteByPrimaryKey(primaryKey);
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("deleteByPrimaryKey.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }

    /**
     * 根据主键更新不为null的字段
     *
     * @param service
     * @param record
     * @return
     */
    protected AjaxResult updateByPrimaryKey(BaseService<T> service, T record,BindingResult br) {
        AjaxResult result = new AjaxResult();
        if (checkResult(br, result)){
            return result;
        }

        try{
            service.updateByPrimaryKey(record);
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("updateByPrimaryKey.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }

    /**
     * 根据主键更新不为null的字段
     *
     * @param service
     * @param record
     * @return
     */
    protected AjaxResult updateByPrimaryKeySelective(BaseService<T> service, T record, BindingResult br) {
        AjaxResult result = new AjaxResult();
        if (checkResult(br, result)){
            return result;
        }

        try{
            service.updateByPrimaryKeySelective(record);
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("updateByPrimaryKeySelective.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }

    /**
     * 校验前端获取到的数据
     * @param br
     * @param result
     * @return
     */
    private boolean checkResult(BindingResult br, AjaxResult result) {
        if(br.hasErrors()){
            StringBuffer sb = new StringBuffer();
            List<ObjectError> list = br.getAllErrors();
            for (int i = 0; i < list.size() ; i++) {
                sb.append(list.get(i).getCode()+"-").append(list.get(i).getDefaultMessage()+"; ");
            }
            result.setCode(StatusCode.REQUEST_ERROR);
            result.setMsg(sb.toString());
            LOGGER.error("数据校验异常："+sb.toString());
            return true;
        }
        return false;
    }

    /**
     *根据主键查询T实体类
     *
     * @param service
     * @param primaryKey
     * @return
     */
    protected AjaxResult selectByPrimaryKey(BaseService<T> service, Object primaryKey){
        AjaxResult result = new AjaxResult();
        try{
            result.setData(service.selectByPrimaryKey(primaryKey));
        }catch (Exception e){
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("selectByPrimaryKey.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }
}
