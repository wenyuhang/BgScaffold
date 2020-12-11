package com.scaffold.demo.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.scaffold.demo.common.mapper.VPNUserMapper;
import com.scaffold.demo.common.service.VPNUserService;
import com.scaffold.demo.entity.VPNUser;
import com.scaffold.demo.enums.BaseResponseCode;
import com.scaffold.demo.exceptions.BusinessException;
import com.scaffold.demo.utils.PageUtils;
import com.scaffold.demo.vo.request.VPNUserAddReqVO;
import com.scaffold.demo.vo.request.VPNUserPageReqVO;
import com.scaffold.demo.vo.request.VPNUserUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:56
 * desc   :
 */
@Service
@Slf4j
public class VPNUserServiceImpl implements VPNUserService {

    @Autowired
    private VPNUserMapper vpnUserMapper;

    @Override
    public PageVO<VPNUser> pageInfo(VPNUserPageReqVO vo) {

        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<VPNUser> vpnUsers = vpnUserMapper.selectAll(vo);
        return PageUtils.getPageVO(vpnUsers);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedUsers(List<String> userIds) {
        int i = vpnUserMapper.deletedUsers(userIds);
        if(i == 0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public void addUser(VPNUserAddReqVO vo) {
        Calendar calendar =Calendar.getInstance();
        VPNUser vpnUser =new VPNUser();
        BeanUtils.copyProperties(vo,vpnUser);
        vpnUser.setEmail(vo.getEmail());
        vpnUser.setId(UUID.randomUUID().toString());
        vpnUser.setCreateTime(new Date());
        calendar.setTime(new Date());
        calendar.add(calendar.YEAR,1);
        vpnUser.setExpirationTime(calendar.getTime());
        vpnUser.setLastLogin(new Date());
        vpnUser.setLevel(vo.getLevel());
        vpnUser.setStatus(vo.getStatus());
        vpnUser.setDeleted(1);
        int i = vpnUserMapper.insertSelective(vpnUser);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public void updateUserInfo(VPNUserUpdateReqVO vo) {
        VPNUser vpnUser = vpnUserMapper.selectByPrimaryKey(vo.getId());
        if (null==vpnUser){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,vpnUser);

        int count= vpnUserMapper.updateByPrimaryKeySelective(vpnUser);
        if (count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }

    }

}

