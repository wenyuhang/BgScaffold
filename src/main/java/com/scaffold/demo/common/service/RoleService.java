package com.scaffold.demo.common.service;

import com.scaffold.demo.entity.Role;
import com.scaffold.demo.vo.request.RoleAddReqVO;
import com.scaffold.demo.vo.request.RolePageReqVO;
import com.scaffold.demo.vo.request.RoleUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 9:38
 * desc   :
 */
public interface RoleService {
    Role addRole(RoleAddReqVO vo);

    void updateRole(RoleUpdateReqVO vo, String accessToken);

    Role detailInfo(String id);

    void deletedRole(String id);

    PageVO<Role> pageInfo(RolePageReqVO vo);

    List<String> getRoleNames(String userId);

    List<Role> getRoleInfoByUserId(String userId);

    List<Role> selectAllRoles();
}
