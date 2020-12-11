package com.scaffold.demo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:41
 * desc   :
 */
@Data
public class VPNUserAddReqVO {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "会员等级")
    private String level;

    @ApiModelProperty(value = "用户状态")
    private Integer status;
}
