package com.nenu.edu.server.po;

import lombok.Data;

import java.util.Set;

/**
 * @Author: Liang Jiayue
 * @Description:课程
 */
@Data
public class Course extends BasePo {

    /**
     * 授课老师
     */
    private Teacher teacher;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程代码
     */
    private String code;

    /**
     * 上课时间
     */
    private String time;

    /**
     * 上课地点
     */
    private String place;

    /**
     * 选课学生
     */
    private Set<Student> students;

}

