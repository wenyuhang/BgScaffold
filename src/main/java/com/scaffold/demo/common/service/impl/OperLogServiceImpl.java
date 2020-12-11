package com.scaffold.demo.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.scaffold.demo.common.mapper.OperLogMapper;
import com.scaffold.demo.common.service.OperLogService;
import com.scaffold.demo.entity.Log;
import com.scaffold.demo.utils.PageUtils;
import com.scaffold.demo.vo.request.LogPageReqVO;
import com.scaffold.demo.vo.response.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:46
 * desc   :
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Autowired
    private OperLogMapper sysLogMapper;

    @Override
    public PageVO<Log> pageInfo(LogPageReqVO vo) {

        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Log> sysLogs = sysLogMapper.selectAll(vo);
        return PageUtils.getPageVO(sysLogs);
    }

    @Override
    public void deleted(List<String> logIds) {
        sysLogMapper.batchDeletedLog(logIds);
    }
}
