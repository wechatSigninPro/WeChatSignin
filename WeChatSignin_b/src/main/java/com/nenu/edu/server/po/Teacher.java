package com.nenu.edu.server.po;

import lombok.Data;

import java.util.List;

/**
 * @Author: Liang Jiayue
 * @Description:老师
 */
@Data
public class Teacher extends BasePo {

    /**
     * 姓名（用于查询，赋user.name值）
     */
    private String name;
    /**
     * 用户
     */
    private User user;

    /**
     * 课程
     */
    private List<Course> courses;
}
