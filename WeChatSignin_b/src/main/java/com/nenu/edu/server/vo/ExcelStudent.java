package com.nenu.edu.server.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: Lu Jingyuan
 * @Description:Excel学生
 */
@Data
public class ExcelStudent {

    /**
     * 学生编号
     */
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生学号
     */
    private String campusId;

    /**
     * 点名列表
     */
    private List<ExcelCall> calls;
}
