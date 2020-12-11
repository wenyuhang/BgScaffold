package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.Role;
import com.scaffold.demo.vo.request.RolePageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:11
 * desc   :
 */
@Repository
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRoleInfoByIds(List<String> ids);

    List<Role> selectAll(RolePageReqVO vo);
}
