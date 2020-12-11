package com.scaffold.demo.common.service;

import com.scaffold.demo.entity.VPNUserFlow;
import com.scaffold.demo.vo.request.VPNUserFlowPageReqVO;
import com.scaffold.demo.vo.request.VPNUserFlowUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:40
 * desc   :
 */
public interface VPNUserFlowService {
    PageVO<VPNUserFlow> pageInfo(VPNUserFlowPageReqVO vo);

    void updateUserInfo(VPNUserFlowUpdateReqVO vo);
}
