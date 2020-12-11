package com.scaffold.demo.common.service;

import com.scaffold.demo.entity.Log;
import com.scaffold.demo.vo.request.LogPageReqVO;
import com.scaffold.demo.vo.response.PageVO;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:33
 * desc   :
 */
public interface OperLogService {
    PageVO<Log> pageInfo(LogPageReqVO vo);

    void deleted(List<String> logIds);
}
