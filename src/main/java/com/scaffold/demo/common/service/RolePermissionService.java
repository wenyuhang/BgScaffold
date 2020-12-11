package com.scaffold.demo.common.service;

import com.scaffold.demo.vo.request.RolePermissionOperationReqVO;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:29
 * desc   :
 */
public interface RolePermissionService {
    List<String> getPermissionIdsByRoles(List<String> roleIds);

    List<String> getPermissionIdsByRoleId(String roleId);

    int removeByRoleId(String roleId);

    void addRolePermission(RolePermissionOperationReqVO vo);

    int removeByPermissionId(String permissionId);

    List<String> getRoleIds(String permissionId);
}
