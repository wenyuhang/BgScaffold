package com.scaffold.demo.common.service;

import com.scaffold.demo.entity.VPNNode;
import com.scaffold.demo.vo.request.VPNNodeAddReqVO;
import com.scaffold.demo.vo.request.VPNNodePageReqVO;
import com.scaffold.demo.vo.request.VPNNodeUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:39
 * desc   :
 */
public interface VPNNodeService {
    PageVO<VPNNode> pageInfo(VPNNodePageReqVO vo);

    void deletedNodes(List<String> nodeIds);

    void updateUserInfo(VPNNodeUpdateReqVO vo);

    void addUser(VPNNodeAddReqVO vo);
}
