package com.scaffold.demo.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.scaffold.demo.common.mapper.VPNUserFlowMapper;
import com.scaffold.demo.common.service.VPNUserFlowService;
import com.scaffold.demo.entity.VPNUserFlow;
import com.scaffold.demo.enums.BaseResponseCode;
import com.scaffold.demo.exceptions.BusinessException;
import com.scaffold.demo.utils.PageUtils;
import com.scaffold.demo.vo.request.VPNUserFlowPageReqVO;
import com.scaffold.demo.vo.request.VPNUserFlowUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:55
 * desc   :
 */
@Service
@Slf4j
public class VPNUserFlowServiceImpl implements VPNUserFlowService {

    @Autowired
    private VPNUserFlowMapper vpnUserFlowMapper;

    @Override
    public PageVO<VPNUserFlow> pageInfo(VPNUserFlowPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<VPNUserFlow> vpnUserFlows = vpnUserFlowMapper.selectAll(vo);
        return PageUtils.getPageVO(vpnUserFlows);
    }

    @Override
    public void updateUserInfo(VPNUserFlowUpdateReqVO vo) {
        VPNUserFlow vpnUserFlow = new VPNUserFlow();
        BeanUtils.copyProperties(vo,vpnUserFlow);
        int count = vpnUserFlowMapper.updateByPrimaryKeySelective(vpnUserFlow);
        if (count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }
}

