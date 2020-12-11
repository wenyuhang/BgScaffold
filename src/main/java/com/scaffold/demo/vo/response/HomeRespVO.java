package com.scaffold.demo.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:36
 * desc   :
 */
@Data
public class HomeRespVO {

    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfo;
    @ApiModelProperty(value = "目录菜单")
    private List<PermissionRespNode> menus;
}
