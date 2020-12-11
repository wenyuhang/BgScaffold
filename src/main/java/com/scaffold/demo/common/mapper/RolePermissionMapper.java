package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:01
 * desc   :
 */
@Repository
@Mapper
public interface RolePermissionMapper {

    List<String> getPermissionIdsByRoleId(String roleId);

    List<String> getPermissionIdsByRoles(List<String> roleIds);

    int deleteByPrimaryKey(String id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int removeByRoleId(String roleId);


    int batchRolePermission(List<RolePermission> list);

    int removeByPermissionId(String permissionId);

    List<String> getRoleIds(String permissionId);
}
