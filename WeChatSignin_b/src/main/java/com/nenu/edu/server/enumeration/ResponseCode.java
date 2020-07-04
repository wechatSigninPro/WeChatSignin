package com.nenu.edu.server.enumeration;

import lombok.Getter;

/**
 * @Author: Ying Guoqing
 * @Description:响应代码
 */
public enum ResponseCode {

    SUCCESS(200, "访问成功"),
    AUTH_STUDENT_LIMITED(301, "无学生身份权限，操作失败"),
    AUTH_TEACHER_LIMITED(302, "无老师身份权限，操作失败"),
    AUTH_ROLE_LIMITED(303, "无身份权限，操作失败"),
    AUTH_FAILED_WITHOUT_TOKEN(304, "认证失败，无认证信息"),
    AUTH_FAILED_TOKEN_ERROR(305, "认证失败，认证信息错误"),
    AUTH_FAILED_TOKEN_EXPIRED(306, "认证失败，认证信息已过期"),
    PARAM_ERROR(401, "参数错误"),
    DATA_ACCESS_FAIL(501, "数据访问失败"),
    INTERNAL_ERROR(502, "系统异常");

    @Getter
    private Integer code;
    @Getter
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
