package com.scaffold.demo.common.service;

import com.scaffold.demo.entity.ChartBean;
import com.scaffold.demo.entity.LoginLog;
import com.scaffold.demo.vo.request.LoginLogPageReqVO;
import com.scaffold.demo.vo.response.PageVO;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:32
 * desc   :
 */
public interface LoginLogService {
    PageVO<LoginLog> pageInfo(LoginLogPageReqVO vo);

    void deleted(List<String> logIds);

    ChartBean getCharData();
}
