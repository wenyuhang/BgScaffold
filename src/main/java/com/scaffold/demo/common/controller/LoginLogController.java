package com.scaffold.demo.common.controller;

import com.scaffold.demo.aop.annotation.LogAnnotation;
import com.scaffold.demo.common.service.LoginLogService;
import com.scaffold.demo.entity.LoginLog;
import com.scaffold.demo.utils.DataResult;
import com.scaffold.demo.vo.request.LoginLogPageReqVO;
import com.scaffold.demo.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:59
 * desc   :
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "系统模块-登录日志")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/loginlogs")
    @ApiOperation(value = "分页查询系统登录日志接口")
    @LogAnnotation(title = "系统操作登录日志管理",action = "分页查询系统登录日志")
    @RequiresPermissions("sys:loginlog:list")
    public DataResult<PageVO<LoginLog>> pageInfo(@RequestBody LoginLogPageReqVO vo) {
        PageVO<LoginLog> sysLogPageVO = loginLogService.pageInfo(vo);
        DataResult<PageVO<LoginLog>> result = DataResult.success();
        result.setData(sysLogPageVO);
        return result;
    }

    @DeleteMapping("/loginlogs")
    @ApiOperation(value = "删除登录日志接口")
    @LogAnnotation(title = "系统登录日志管理",action = "删除系统登录日志")
    @RequiresPermissions("sys:loginlog:deleted")
    public DataResult deleted(@RequestBody List<String> logIds){
        loginLogService.deleted(logIds);
        return DataResult.success();
    }
}

