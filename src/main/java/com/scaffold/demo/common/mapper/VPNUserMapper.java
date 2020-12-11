package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.VPNUser;
import com.scaffold.demo.vo.request.VPNUserPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:10
 * desc   :
 */
@Repository
@Mapper
public interface VPNUserMapper {

    List<VPNUser> selectAll(VPNUserPageReqVO vo);

    int deletedUsers(@Param("list") List<String> list);

    int insertSelective(VPNUser vpnUser);

    VPNUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VPNUser vpnUser);
}
