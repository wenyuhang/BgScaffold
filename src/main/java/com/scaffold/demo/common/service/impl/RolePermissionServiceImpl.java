package com.scaffold.demo.common.service.impl;

import com.scaffold.demo.common.mapper.RolePermissionMapper;
import com.scaffold.demo.common.service.RolePermissionService;
import com.scaffold.demo.entity.RolePermission;
import com.scaffold.demo.enums.BaseResponseCode;
import com.scaffold.demo.exceptions.BusinessException;
import com.scaffold.demo.vo.request.RolePermissionOperationReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:48
 * desc   :
 */
@Service
@Slf4j
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<String> getPermissionIdsByRoles(List<String> roleIds) {
        return rolePermissionMapper.getPermissionIdsByRoles(roleIds);
    }

    @Override
    public List<String> getPermissionIdsByRoleId(String roleId) {
        return rolePermissionMapper.getPermissionIdsByRoleId(roleId);
    }

    @Override
    public int removeByRoleId(String roleId) {
        return rolePermissionMapper.removeByRoleId(roleId);
    }



    @Override
    public void addRolePermission(RolePermissionOperationReqVO vo) {

        Date createTime=new Date();
        List<RolePermission> list=new ArrayList<>();
        for (String permissionId:vo.getPermissionIds()){
            RolePermission rolePermission=new RolePermission();
            rolePermission.setId(UUID.randomUUID().toString());
            rolePermission.setCreateTime(createTime);
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(vo.getRoleId());
            list.add(rolePermission);
        }
        rolePermissionMapper.removeByRoleId(vo.getRoleId());
        int count=rolePermissionMapper.batchRolePermission(list);
        if (count==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public int removeByPermissionId(String permissionId) {

        return rolePermissionMapper.removeByPermissionId(permissionId);
    }

    @Override
    public List<String> getRoleIds(String permissionId) {

        return rolePermissionMapper.getRoleIds(permissionId);
    }
}
