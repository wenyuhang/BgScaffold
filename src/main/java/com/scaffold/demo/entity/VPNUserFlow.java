package com.scaffold.demo.entity;

import lombok.Data;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:08
 * desc   :
 */
@Data
public class VPNUserFlow {
    private String id;

    private String username;

    private Integer credit;

    private Integer connect;

    private Integer money;

    private Integer flow;

    private Integer maxUp;

    private Integer maxDown;

    private Integer deleted;
}
