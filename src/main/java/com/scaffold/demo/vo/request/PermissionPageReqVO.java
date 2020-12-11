package com.scaffold.demo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:44
 * desc   :
 */
@Data
public class PermissionPageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize=10;
}
