package com.scaffold.demo.common.service;

import com.scaffold.demo.entity.Permission;
import com.scaffold.demo.vo.request.PermissionAddReqVO;
import com.scaffold.demo.vo.request.PermissionPageReqVO;
import com.scaffold.demo.vo.request.PermissionUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;
import com.scaffold.demo.vo.response.PermissionRespNode;

import java.util.List;
import java.util.Set;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:41
 * desc   :
 */
public interface PermissionService {
    List<Permission> getPermission(String userId);

    Set<String> getPermissionsByUserId(String userId);

    List<PermissionRespNode> permissionTreeList(String userId);

    Permission addPermission(PermissionAddReqVO vo);

    void deleted(String permissionId);

    void updatePermission(PermissionUpdateReqVO vo);

    PageVO<Permission> pageInfo(PermissionPageReqVO vo);

    Permission detailInfo(String permissionId);

    List<Permission> selectAll();

    List<PermissionRespNode> selectAllByTree();

    List<PermissionRespNode> selectAllMenuByTree(String permissionId);
}
