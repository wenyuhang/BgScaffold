package com.scaffold.demo.common.service;

import com.scaffold.demo.vo.request.UserRoleOperationReqVO;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:27
 * desc   :
 */
public interface UserRoleService {
    List<String> getRoleIdsByUserId(String userId);

    List<String> getUserIdsByRoleIds(List<String> roleIds);

    List<String> getInfoByUserIdByRoleId( String roleId);

    void addUserRoleInfo(UserRoleOperationReqVO vo);

    int removeByUserId(String userId);
}
