package com.scaffold.demo.common.controller;

import com.scaffold.demo.aop.annotation.LogAnnotation;
import com.scaffold.demo.common.service.RoleService;
import com.scaffold.demo.constant.Constant;
import com.scaffold.demo.entity.Role;
import com.scaffold.demo.utils.DataResult;
import com.scaffold.demo.vo.request.RoleAddReqVO;
import com.scaffold.demo.vo.request.RolePageReqVO;
import com.scaffold.demo.vo.request.RoleUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 12:01
 * desc   :
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    @ApiOperation(value = "新增角色接口")
    @LogAnnotation(title = "角色管理",action = "新增角色")
    @RequiresPermissions("sys:role:add")
    public DataResult<Role> addRole(@RequestBody @Valid RoleAddReqVO vo){
        DataResult<Role> result=DataResult.success();
        result.setData(roleService.addRole(vo));
        return result;
    }
    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    @LogAnnotation(title = "角色管理",action = "删除角色")
    @RequiresPermissions("sys:role:deleted")
    public DataResult deleted(@PathVariable("id") String id){
        roleService.deletedRole(id);
        return DataResult.success();
    }
    @PutMapping("/role")
    @ApiOperation(value = "更新角色信息接口")
    @LogAnnotation(title = "角色管理",action = "更新角色信息")
    @RequiresPermissions("sys:role:update")
    public DataResult updateDept(@RequestBody @Valid RoleUpdateReqVO vo, HttpServletRequest request){
        roleService.updateRole(vo,request.getHeader(Constant.ACCESS_TOKEN));
        return DataResult.success();
    }
    @GetMapping("/role/{id}")
    @ApiOperation(value = "查询角色详情接口")
    @LogAnnotation(title = "角色管理",action = "查询角色详情")
    @RequiresPermissions("sys:role:detail")
    public DataResult<Role> detailInfo(@PathVariable("id") String id){
        DataResult<Role> result=DataResult.success();
        result.setData(roleService.detailInfo(id));
        return result;
    }
    @PostMapping("/roles")
    @ApiOperation(value = "分页获取角色信息接口")
    @LogAnnotation(title = "角色管理",action = "分页获取角色信息")
    @RequiresPermissions("sys:role:list")
    public DataResult<PageVO<Role>> pageInfo(@RequestBody RolePageReqVO vo){
        DataResult<PageVO<Role>> result=DataResult.success();
        result.setData(roleService.pageInfo(vo));
        return result;
    }
}

