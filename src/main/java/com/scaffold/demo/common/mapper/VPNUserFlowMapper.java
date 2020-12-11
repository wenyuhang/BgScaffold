package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.VPNUserFlow;
import com.scaffold.demo.vo.request.VPNUserFlowPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:07
 * desc   :
 */
@Repository
@Mapper
public interface VPNUserFlowMapper {
    List<VPNUserFlow> selectAll(VPNUserFlowPageReqVO vo);

    int updateByPrimaryKeySelective(VPNUserFlow vpnUserFlow);
}
