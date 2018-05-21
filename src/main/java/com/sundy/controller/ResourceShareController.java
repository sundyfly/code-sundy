package com.sundy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sundy.common.annotation.IgnoreSecurity;
import com.sundy.common.constant.StatusCode;
import com.sundy.common.util.ExceptionUtils;
import com.sundy.common.util.StringUtils;
import com.sundy.model.AjaxResult;
import com.sundy.model.PageAjaxResult;
import com.sundy.model.entity.ShareFile;
import com.sundy.model.vo.ShareFileVo;
import com.sundy.service.ShareFileService;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年04月09 15:13:25
 * 描述：资源分享
 */
@Api(description = "平台资源分享", tags = "ResourceShareController", basePath = "/shares")
@RequestMapping("/shares")
@Controller
public class ResourceShareController extends BaseController<ShareFile> {

    private static final Logger LOGGER = Logger.getLogger(ResourceShareController.class);
    @Resource
    private ShareFileService shareFileService;

    @ApiOperation(value = "查询所有开发工具")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页号", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页大小", required = true, dataType = "int")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @IgnoreSecurity
    @ResponseBody
    public PageAjaxResult getEntityList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        PageAjaxResult result = new PageAjaxResult();
        try {
            PageHelper.startPage(page, limit);
            PageInfo<ShareFile> pages = shareFileService.findEntityAll();
            List<ShareFileVo> list = new ArrayList<>();
            ShareFileVo shareFileVo;
            for (int i = 0; i < pages.getList().size(); i++) {
                shareFileVo = new ShareFileVo();
                ShareFile item = pages.getList().get(i);
                shareFileVo.setId(item.getId());
                shareFileVo.setDownloadTimes(item.getDownloadTimes());
                shareFileVo.setFileName(item.getFileName());
                shareFileVo.setFilePath(item.getFilePath());
                shareFileVo.setFileTime(item.getFileTime());
                shareFileVo.setFileSizeStr(StringUtils.convertFlowSize(item.getFileSize()));
                list.add(shareFileVo);
            }
            result.setData(list);
            result.setCode(0);
            result.setCount((int) pages.getTotal());
            LOGGER.debug("测试异常。。。。。。。。。。。。。。。。。。。。。");
            LOGGER.info("测试异常。。。。。。。。。。。。。。。。。。。。。");
            LOGGER.error("测试异常。。。。。。。。。。。。。。。。。。。。。");
        } catch (Exception e) {
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("getEntityList.服务器异常."+ExceptionUtils.getException(e));
            LOGGER.error(ExceptionUtils.getException(e));
        }
        return result;
    }

    @ApiOperation(value = "资源下载方法")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "fileID", value = "文件的ID", required = true, dataType = "int")
    })
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @IgnoreSecurity
    @ResponseBody
    public AjaxResult downloadShareFile(@RequestParam("fileID") Integer fileID, HttpServletResponse resp) {
        AjaxResult result = new AjaxResult();
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            ShareFile shareFile = shareFileService.selectByPrimaryKey(fileID);
            if (shareFile == null) {
                return result;
            }
            result.setData(shareFile);
            String filePath = shareFile.getFilePath();
            if (new File(filePath).isFile()) {
                int i = filePath.lastIndexOf(".");
                StringBuilder filename = new StringBuilder();
                if (i > 0) {
                    filename.append(shareFile.getFileName()).append(filePath.substring(i));
                } else {
                    filename.append(shareFile.getFileName());
                }
                // 设置相应头，控制浏览器下载该文件，这里就是会出现当你点击下载后，出现的下载地址框
                resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename.toString(), "utf-8"));
                fis = new FileInputStream(shareFile.getFilePath());
                os = resp.getOutputStream();
                byte buffer[] = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    os.write(buffer, 0, len);
                }
                os.flush();
                ShareFile record = new ShareFile();
                record.setId(fileID);
                record.setDownloadTimes(shareFile.getDownloadTimes() + 1);
                shareFileService.updateByPrimaryKeySelective(record);
            }
        } catch (Exception e) {
            result.setCode(StatusCode.SYSTEM_EXCEPTION);
            result.setMsg("downloadShareFile.服务器异常.");
            LOGGER.error(ExceptionUtils.getException(e));
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ie) {
                result.setCode(StatusCode.SYSTEM_EXCEPTION);
                result.setMsg("close IO exception.服务器异常.");
                LOGGER.error(ExceptionUtils.getException(ie));
            }
        }
        return result;
    }

    @ApiOperation(value = "上传资源文件")
    @IgnoreSecurity(security = IgnoreSecurity.USER)
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insert(@ApiParam(value = "新增ShareFile实体",required = true) @RequestBody @Valid ShareFile shareFile, BindingResult br) {
        return super.insert(shareFileService, shareFile, br);
    }

    @ApiOperation(value = "根据fileID删除ShareFile实体，需谨慎")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ShareFile主键",name = "fileID" ,paramType = "path",dataType = "int")
     })
    @RequestMapping(value = "/{fileID}",method = RequestMethod.DELETE)
    @IgnoreSecurity(security = IgnoreSecurity.USER)
    @ResponseBody
    public AjaxResult deleteByPrimaryKey(@PathVariable("fileID") Integer primaryKey) {
        return super.deleteByPrimaryKey(shareFileService, primaryKey);
    }

    @ApiOperation(value = "修改ShareFile实体，只修改不为NULL的字段")
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    @IgnoreSecurity(security = IgnoreSecurity.USER)
    @ResponseBody
    public AjaxResult updateByPrimaryKeySelective(@ApiParam(value = "修改ShareFile实体",required = true) @RequestBody @Valid ShareFile record, BindingResult br) {
        return super.updateByPrimaryKeySelective(shareFileService, record, br);
    }

    @ApiOperation(value = "根据主键查询ShareFile实体")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",value = "ShareFile主键",name = "fileID",required = true,dataType = "int")
    })
    @IgnoreSecurity
    @RequestMapping(value = "/{fileID}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult selectByPrimaryKey(@PathVariable("fileID") Integer primaryKey) {
        return super.selectByPrimaryKey(shareFileService, primaryKey);
    }
}
