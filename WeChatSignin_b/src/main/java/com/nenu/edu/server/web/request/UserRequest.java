package com.nenu.edu.server.web.request;

import com.nenu.edu.server.enumeration.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: Liang Jiayue
 * @Description:用户请求
 */
@Data
public class UserRequest {

    /**
     * 微信用户登录凭证
     */
    @NotEmpty(message = "微信用户登录凭证不能为空", groups = {Login.class})
    private String code;

    /**
     * 角色
     */
    @NotNull(message = "角色错误或不能为空", groups = {Update.class})
    private Role role;

    /**
     * 姓名
     */
    @NotEmpty(message = "姓名不能为空", groups = {Update.class})
    private String name;

    /**
     * 学校
     */
    @NotEmpty(message = "学校不能为空", groups = {Update.class})
    private String school;

    /**
     * 校园账号
     */
    @NotEmpty(message = "校园账号不能为空", groups = {Update.class})
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
     * 专业（学生）
     */
    private String major;

    /**
     * 年级（学生）
     */
    private String grade;

    /**
     * 班级（学生）
     */
    private String clazz;

    /**
     * 登录组
     */
    public interface Login {
    }

    /**
     * 更新组
     */
    public interface Update {
    }
}
