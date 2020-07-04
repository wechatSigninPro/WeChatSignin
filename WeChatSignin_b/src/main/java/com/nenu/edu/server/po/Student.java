package com.nenu.edu.server.po;

import lombok.Data;

import java.util.List;

/**
 * @Author: Liang Jiayue
 * @Description:学生
 */
@Data
public class Student extends BasePo {
    /**
     * 专业
     */
    private String major;

    /**
     * 年级
     */
    private String grade;

    /**
     * 班级
     */
    private String clazz;

    /**
     * 用户
     */
    private User user;

    /**
     * 课程
     */
    private List<Course> courses;

    /**
     * 姓名（用于查询，赋user.name值）
     */
    private String name;
}