package com.scaffold.demo.vo.response;

import lombok.Data;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:00
 * desc   :
 */
@Data
public class PageVO<T> {
    /**
     * serial_number
     */
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private Long totalRows;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 当前第几页
     */
    private Integer pageNum;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 当前页记录数
     */
    private Integer curPageSize;
    /**
     * 数据列表
     */
    private List<T> list;
}
