package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.Log;
import com.scaffold.demo.vo.request.LogPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:58
 * desc   :
 */
@Repository
@Mapper
public interface OperLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectAll(LogPageReqVO vo);

    void batchDeletedLog(List<String> logIds);
}
