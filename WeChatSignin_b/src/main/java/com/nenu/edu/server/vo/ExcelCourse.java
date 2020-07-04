package com.nenu.edu.server.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: Lu Jingyuan
 * @Description:Excel课程
 */
@Data
public class ExcelCourse {

    /**
     * 课程编号
     */
    private Long id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程代码
     */
    private String code;

    /**
     * 学生
     */
    private List<ExcelStudent> students;

}
