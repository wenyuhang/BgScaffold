package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.LoginLog;
import com.scaffold.demo.vo.request.LoginLogPageReqVO;
import com.scaffold.demo.vo.response.NameAndCntVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:54
 * desc   :
 */
@Repository
@Mapper
public interface LoginLogMapper {
    int saveLoginLog(LoginLog log);

    List<LoginLog> selectAll(LoginLogPageReqVO vo);

    void batchDeletedLog(List<String> logIds);

    int getTotalSuccessLoginCnt();

    int getTodaySuccessLoginCnt();

    int getWeekSuccessLoginCnt();

    int getTodayFailLoginCnt();

    int getTotalFailLoginCnt();

    List<NameAndCntVO> getDateAndCntList();
}
