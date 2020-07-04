package com.nenu.edu.server.po;

import lombok.Data;

import java.util.Date;

/**
 *
 * @Author: Liang Jiayue
 * @Description:简单对象
 */
@Data
public abstract class BasePo {

    /**
     * 编号
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}