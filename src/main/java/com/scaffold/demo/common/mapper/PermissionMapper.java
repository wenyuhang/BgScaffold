package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:00
 * desc   :
 */
@Repository
@Mapper
public interface PermissionMapper {

    List<Permission> selectInfoByIds (List<String> ids);

    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectAll();

    List<Permission> selectChild(String pid);
}
