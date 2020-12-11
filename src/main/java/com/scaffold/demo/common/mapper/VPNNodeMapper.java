package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.VPNNode;
import com.scaffold.demo.vo.request.VPNNodePageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:05
 * desc   :
 */
@Repository
@Mapper
public interface VPNNodeMapper {
    List<VPNNode> selectAll(VPNNodePageReqVO vo);

    int deletedNodes(List<String> nodeIds);

    VPNNode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VPNNode vpnNode);

    int insertSelective(VPNNode vpnNode);
}
