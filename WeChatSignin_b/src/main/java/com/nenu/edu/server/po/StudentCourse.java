package com.nenu.edu.server.po;

import lombok.Data;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:46 2020/7/4
 */
@Data
public class StudentCourse extends BasePo {

    /**
     * 课程
     */
    private Course course;

    /**
     * 申请人
     */
    private Student student;
}