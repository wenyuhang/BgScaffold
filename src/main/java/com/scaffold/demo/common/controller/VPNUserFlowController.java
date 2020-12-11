package com.scaffold.demo.common.controller;

import com.scaffold.demo.aop.annotation.LogAnnotation;
import com.scaffold.demo.common.service.VPNUserFlowService;
import com.scaffold.demo.entity.VPNUserFlow;
import com.scaffold.demo.utils.DataResult;
import com.scaffold.demo.vo.request.VPNUserFlowPageReqVO;
import com.scaffold.demo.vo.request.VPNUserFlowUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 12:12
 * desc   :
 */
@Api(tags = "VPN管理模块-流量管理")
@RequestMapping("/sys")
@RestController
public class VPNUserFlowController {

    @Autowired
    private VPNUserFlowService vpnUserFlowService;

    @PostMapping("/vpnuserflows")
    @ApiOperation(value = "分页获取VPN用户流量信息接口")
    @LogAnnotation(title = "VPN用户流量管理",action = "分页获取VPN用户流量信息")
    @RequiresPermissions("sys:vpnuserflow:list")
    public DataResult<PageVO<VPNUserFlow>> pageInfo(@RequestBody VPNUserFlowPageReqVO vo){
        DataResult<PageVO<VPNUserFlow>> result= DataResult.success();
        result.setData(vpnUserFlowService.pageInfo(vo));
        return result;
    }

    @PutMapping("/vpnuserflow")
    @ApiOperation(value = "更新VPN用户流量信息接口")
    @LogAnnotation(title = "VPN管理",action = "更新VPN用户流量信息")
    @RequiresPermissions("sys:vpnuserflow:update")
    public DataResult updateUserInfo(@RequestBody @Valid VPNUserFlowUpdateReqVO vo){
        System.out.println(vo.toString());
        vpnUserFlowService.updateUserInfo(vo);
        return DataResult.success();
    }
}

