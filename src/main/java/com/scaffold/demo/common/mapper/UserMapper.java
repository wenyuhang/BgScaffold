package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.User;
import com.scaffold.demo.vo.request.UserPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:03
 * desc   :
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * 通过用户名获取用户信息
     * @param username 用户名
     * @return
     */
    User getUserInfoByName(String username);

    User selectByPrimaryKey(String userId);

    List<User> selectAll(UserPageReqVO vo);

    List<User> selectUserInfoByDeptIds (List<String> deptIds);

    List<User> getUserListByDeptId(String deptId);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int deletedUsers(@Param("sysUser") User sysUser, @Param("list") List<String> list);
}
