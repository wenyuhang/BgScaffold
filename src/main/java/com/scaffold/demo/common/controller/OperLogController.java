package com.scaffold.demo.common.controller;

import com.scaffold.demo.aop.annotation.LogAnnotation;
import com.scaffold.demo.common.service.OperLogService;
import com.scaffold.demo.entity.Log;
import com.scaffold.demo.utils.DataResult;
import com.scaffold.demo.vo.request.LogPageReqVO;
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
 * date   : 2020/12/8 12:00
 * desc   :
 */
@RequestMapping("/sys")
@Api(tags = "系统模块-系统操作日志管理")
@RestController
public class OperLogController {

    @Autowired
    private OperLogService logService;

    @PostMapping("/logs")
    @ApiOperation(value = "分页查询系统操作日志接口")
    @LogAnnotation(title = "系统操作日志管理",action = "分页查询系统操作日志")
    @RequiresPermissions("sys:log:list")
    public DataResult<PageVO<Log>> pageInfo(@RequestBody LogPageReqVO vo){
        PageVO<Log> sysLogPageVO = logService.pageInfo(vo);
        DataResult<PageVO<Log>> result= DataResult.success();
        result.setData(sysLogPageVO);
        return result;
    }

    @DeleteMapping("/logs")
    @ApiOperation(value = "删除日志接口")
    @LogAnnotation(title = "系统操作日志管理",action = "删除系统操作日志")
    @RequiresPermissions("sys:log:deleted")
    public DataResult deleted(@RequestBody List<String> logIds){
        logService.deleted(logIds);
        return DataResult.success();
    }

}

