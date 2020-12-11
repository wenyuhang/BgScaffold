package com.scaffold.demo.common.service;

import com.scaffold.demo.entity.VPNUser;
import com.scaffold.demo.vo.request.VPNUserAddReqVO;
import com.scaffold.demo.vo.request.VPNUserPageReqVO;
import com.scaffold.demo.vo.request.VPNUserUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:41
 * desc   :
 */
public interface VPNUserService {

    PageVO<VPNUser> pageInfo(VPNUserPageReqVO vo);

    void deletedUsers(List<String> userIds);

    void addUser(VPNUserAddReqVO vo);

    void updateUserInfo(VPNUserUpdateReqVO vo);
}
