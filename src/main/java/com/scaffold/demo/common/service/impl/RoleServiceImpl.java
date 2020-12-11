package com.scaffold.demo.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.scaffold.demo.common.mapper.RoleMapper;
import com.scaffold.demo.common.mapper.UserRoleMapper;
import com.scaffold.demo.common.service.*;
import com.scaffold.demo.constant.Constant;
import com.scaffold.demo.entity.Role;
import com.scaffold.demo.enums.BaseResponseCode;
import com.scaffold.demo.exceptions.BusinessException;
import com.scaffold.demo.utils.PageUtils;
import com.scaffold.demo.utils.TokenSettings;
import com.scaffold.demo.vo.request.RoleAddReqVO;
import com.scaffold.demo.vo.request.RolePageReqVO;
import com.scaffold.demo.vo.request.RolePermissionOperationReqVO;
import com.scaffold.demo.vo.request.RoleUpdateReqVO;
import com.scaffold.demo.vo.response.PageVO;
import com.scaffold.demo.vo.response.PermissionRespNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:03
 * desc   :
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private TokenSettings tokenSettings;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PermissionService permissionService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role addRole(RoleAddReqVO vo) {

        Role Role=new Role();
        BeanUtils.copyProperties(vo,Role);
        Role.setId(UUID.randomUUID().toString());
        Role.setCreateTime(new Date());
        int count= roleMapper.insertSelective(Role);
        if(count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        if(null!=vo.getPermissions()&&!vo.getPermissions().isEmpty()){
            RolePermissionOperationReqVO reqVO=new RolePermissionOperationReqVO();
            reqVO.setRoleId(Role.getId());
            reqVO.setPermissionIds(vo.getPermissions());
            rolePermissionService.addRolePermission(reqVO);
        }

        return Role;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleUpdateReqVO vo, String accessToken) {
        Role Role=roleMapper.selectByPrimaryKey(vo.getId());
        if (null==Role){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        Role update=new Role();
        BeanUtils.copyProperties(vo,update);
//        BeanUtils.copyProperties(vo,Role);
        update.setUpdateTime(new Date());
        int count=roleMapper.updateByPrimaryKeySelective(update);
        if(count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        rolePermissionService.removeByRoleId(Role.getId());
        if(null!=vo.getPermissions()&&!vo.getPermissions().isEmpty()){
            RolePermissionOperationReqVO reqVO=new RolePermissionOperationReqVO();
            reqVO.setRoleId(Role.getId());
            reqVO.setPermissionIds(vo.getPermissions());
            rolePermissionService.addRolePermission(reqVO);

            List<String> userIds=userRoleMapper.getInfoByUserIdByRoleId(vo.getId());

            if(!userIds.isEmpty()){
                for (String userId:userIds){
                    redisService.set(Constant.JWT_REFRESH_KEY +userId,userId,tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                    //清空权鉴缓存
                    redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
                }

            }

        }

    }

    @Override
    public Role detailInfo(String id) {
        Role Role = roleMapper.selectByPrimaryKey(id);
        if(Role==null){
            log.error("传入 的 id:{}不合法",id);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        List<PermissionRespNode> permissionRespNodes = permissionService.selectAllByTree();
        Set<String> checkList=new HashSet<>(rolePermissionService.getPermissionIdsByRoleId(Role.getId()));
        setheckced(permissionRespNodes,checkList);
        Role.setPermissionRespNodes(permissionRespNodes);
        return Role;
    }


    private void setheckced(List<PermissionRespNode> list, Set<String> checkList){

        for(PermissionRespNode node:list){

            if(checkList.contains(node.getId())&&(node.getChildren()==null||node.getChildren().isEmpty())){
                node.setChecked(true);
            }
            setheckced((List<PermissionRespNode>) node.getChildren(),checkList);

        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletedRole(String id) {
        Role Role=new Role();
        Role.setId(id);
        Role.setUpdateTime(new Date());
        Role.setDeleted(0);
        int count=roleMapper.updateByPrimaryKeySelective(Role);
        if (count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        List<String> userIds=userRoleMapper.getInfoByUserIdByRoleId(id);
        rolePermissionService.removeByRoleId(id);
        if(!userIds.isEmpty()){
            for (String userId:userIds){
                redisService.set(Constant.JWT_REFRESH_KEY +userId,userId,tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                //清空权鉴缓存
                redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
            }

        }
    }

    @Override
    public PageVO<Role> pageInfo(RolePageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Role> Roles =roleMapper.selectAll(vo);
        return PageUtils.getPageVO(Roles);
    }

    @Override
    public List<String> getRoleNames(String userId) {
        List<Role> roles=getRoleInfoByUserId(userId);
        if (null==roles||roles.isEmpty()){
            return null;
        }
        List<String> list=new ArrayList<>();
        for (Role role:roles){
            list.add(role.getName());
        }
        return list;
    }

    @Override
    public List<Role> getRoleInfoByUserId(String userId) {
        List<String> roleIds=userRoleService.getRoleIdsByUserId(userId);
        if (roleIds.isEmpty()){
            return null;
        }
        return roleMapper.getRoleInfoByIds(roleIds);
    }

    @Override
    public List<Role> selectAllRoles() {
        return roleMapper.selectAll(new RolePageReqVO());
    }
}
