package com.scaffold.demo.vo.response;

import com.scaffold.demo.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:37
 * desc   :
 */
@Data
public class UserOwnRoleRespVO {

    @ApiModelProperty("所有角色集合")
    private List<Role> allRole;
    @ApiModelProperty(value = "用户所拥有角色集合")
    private List<String> ownRoles;
}
