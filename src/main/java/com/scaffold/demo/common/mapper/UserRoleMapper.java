package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:29
 * desc   :
 */
@Repository
@Mapper
public interface UserRoleMapper {
    List<String> getRoleIdsByUserId(String userId);

    List<String> getInfoByUserIdByRoleId(String roleId);

    List<String> getUserIdsByRoleIds(List<String> roleIds);

    int batchUserRole(List<UserRole> list);

    int removeByUserId(String userId);
}
