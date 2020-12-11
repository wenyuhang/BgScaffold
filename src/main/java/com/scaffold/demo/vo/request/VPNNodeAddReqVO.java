package com.scaffold.demo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:40
 * desc   :
 */
@Data
public class VPNNodeAddReqVO {

    @ApiModelProperty(value = "节点名称")
    @NotBlank(message = "节点名不能为空")
    private String node;

    @ApiModelProperty(value = "IP")
    private String ip;

    @ApiModelProperty(value = "端口号")
    private Integer port;

    @ApiModelProperty(value = "最大上传速度")
    private Integer maxUp;

    @ApiModelProperty(value = "最大下载速度")
    private Integer maxDown;

    @ApiModelProperty(value = "协议")
    private String protocol;

    @ApiModelProperty(value = "链接")
    private String link;

    @ApiModelProperty(value = "状态")
    private Integer status;
}

