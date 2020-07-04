package com.nenu.edu.server.po;

import lombok.Data;

import java.util.List;

/**
 * @Author: Liang Jiayue
 * @Description:点名
 */
@Data
public class Call extends BasePo {

    /**
     * 课程
     */
    private Course course;

    /**
     * 口令
     */
    private String password;

    /**
     * 坐标纬度
     */
    private Float latitude;

    /**
     * 坐标经度
     */
    private Float longitude;

    /**
     * 签到
     */
    private List<Sign> signs;

}

