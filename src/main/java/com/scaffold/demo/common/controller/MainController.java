package com.scaffold.demo.common.controller;

import com.scaffold.demo.aop.annotation.LogAnnotation;
import com.scaffold.demo.common.service.LoginLogService;
import com.scaffold.demo.entity.ChartBean;
import com.scaffold.demo.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:59
 * desc   :
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "首页")
public class MainController {

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/main")
    @ApiOperation(value = "首页获取图表信息接口")
    @LogAnnotation(title = "V首页",action = "首页获取图表信息接口")
    public DataResult<ChartBean> getChart(){
        DataResult<ChartBean> result= DataResult.success();
        result.setData(loginLogService.getCharData());
        System.out.println(result.toString());
        return result;
    }
}
