package com.scaffold.demo.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.scaffold.demo.common.mapper.LoginLogMapper;
import com.scaffold.demo.common.service.LoginLogService;
import com.scaffold.demo.entity.ChartBean;
import com.scaffold.demo.entity.LoginLog;
import com.scaffold.demo.utils.PageUtils;
import com.scaffold.demo.vo.request.LoginLogPageReqVO;
import com.scaffold.demo.vo.response.NameAndCntVO;
import com.scaffold.demo.vo.response.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:45
 * desc   :
 */
@Service
@Slf4j
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;


    @Override
    public PageVO<LoginLog> pageInfo(LoginLogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<LoginLog> loginLogs = loginLogMapper.selectAll(vo);
        return PageUtils.getPageVO(loginLogs);
    }

    @Override
    public void deleted(List<String> logIds) {
        loginLogMapper.batchDeletedLog(logIds);
    }

    @Override
    public ChartBean getCharData() {
        int totalSuccessLoginCnt = loginLogMapper.getTotalSuccessLoginCnt();
        int todaySuccessLoginCnt = loginLogMapper.getTodaySuccessLoginCnt();
        int weekSuccessLoginCnt = loginLogMapper.getWeekSuccessLoginCnt();
        int todayFailLoginCnt = loginLogMapper.getTodayFailLoginCnt();
        int totalFailLoginCnt = loginLogMapper.getTotalFailLoginCnt();
        float resourceRate = (float) (new Random(System.currentTimeMillis()).nextInt(80) + 20) / 100;

        List<NameAndCntVO> dateAndCntList = loginLogMapper.getDateAndCntList();
        List<String> dateNameList = new ArrayList<>();
        List<Integer> loginCntList = new ArrayList<>();
        for (NameAndCntVO nameAndCntVO : dateAndCntList) {
            dateNameList.add(nameAndCntVO.getDate7());
            loginCntList.add(nameAndCntVO.getSuccessLoginCnt());
        }
        ChartBean bean = new ChartBean();
        bean.setTotalSuccessLoginCnt(totalSuccessLoginCnt);
        bean.setTodaySuccessLoginCnt(todaySuccessLoginCnt);
        bean.setWeekSuccessLoginCnt(weekSuccessLoginCnt);
        bean.setTodayFailLoginCnt(todayFailLoginCnt);
        bean.setTotalFailLoginCnt(totalFailLoginCnt);
        bean.setResourceRate(resourceRate);
        Collections.reverse(dateNameList);
        Collections.reverse(loginCntList);
        bean.setDateNameList(dateNameList);
        bean.setLoginCntList(loginCntList);
        return bean;
    }
}
