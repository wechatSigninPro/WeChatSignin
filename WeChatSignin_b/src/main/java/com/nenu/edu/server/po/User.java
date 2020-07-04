package com.nenu.edu.server.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nenu.edu.server.enumeration.Role;
import lombok.Data;


/**

 * @Author: Liang Jiayue
 * @Description:用户
 */
@Data
public class User extends BasePo {

    /**
     * 微信用户编号
     */
    @JsonIgnore
    private String openId;

    /**
     * 角色
     */
    private Role role;

    /**
     * 姓名
     */
    private String name;

    /**
     * 学校
     */
    private String school;

    /**
     * 校园账号
     */
    private String campusId;

    /**
     * 学院
     */
    private String department;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 老师属性
     */
    private Teacher teacher;

    /**
     * 学生属性
     */
    private Student student;

}