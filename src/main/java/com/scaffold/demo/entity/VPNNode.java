package com.scaffold.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:06
 * desc   :
 */
@Data
public class VPNNode {

    private Integer id;

    private String node;

    private String ip;

    private Integer port;

    private Date createTime;

    private String protocol;

    private Integer status;

    private Integer maxUp;

    private Integer maxDown;

    private Integer delay;

    private String link;

    private Integer deleted;

}
