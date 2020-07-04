package com.nenu.edu.server.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Lu Jingyuan
 * @Description:Excel点名
 */
@Data
public class ExcelCall {

    /**
     * 点名编号
     */
    private Long id;

    /**
     * 签到编号
     */
    private Long signId;

    /**
     * 点名时间
     */
    private Date createTime;

}