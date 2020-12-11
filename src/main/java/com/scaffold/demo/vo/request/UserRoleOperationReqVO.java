package com.scaffold.demo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:28
 * desc   :
 */
@Data
public class UserRoleOperationReqVO {
    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;
    @ApiModelProperty(value = "角色id集合")
    @NotEmpty(message = "角色id集合不能为空")
    private List<String> roleIds;
}
