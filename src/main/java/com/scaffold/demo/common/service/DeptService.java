package com.scaffold.demo.common.service;

import com.scaffold.demo.entity.Dept;
import com.scaffold.demo.entity.User;
import com.scaffold.demo.vo.request.DeptAddReqVO;
import com.scaffold.demo.vo.request.DeptPageReqVO;
import com.scaffold.demo.vo.request.DeptUpdateReqVO;
import com.scaffold.demo.vo.request.UserPageUserByDeptReqVO;
import com.scaffold.demo.vo.response.DeptRespNodeVO;
import com.scaffold.demo.vo.response.PageVO;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:26
 * desc   :
 */
public interface DeptService {
    Dept detailInfo(String id);

    List<DeptRespNodeVO> deptTreeList(String deptId);

    Dept addDept(DeptAddReqVO vo);

    void updateDept(DeptUpdateReqVO vo);

    void deleted(String id);

    PageVO<Dept> pageInfo(DeptPageReqVO vo);

    PageVO<User> pageDeptUserInfo(UserPageUserByDeptReqVO vo);

    List<Dept> selectAll();
}
