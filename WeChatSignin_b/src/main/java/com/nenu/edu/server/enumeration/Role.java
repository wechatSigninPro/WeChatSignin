package com.nenu.edu.server.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nenu.edu.server.exception.AuthException;
import com.nenu.edu.server.po.User;
import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author: Ying Guoqing
 * @Description:角色枚举
 */
public enum Role {

    STUDENT(0, "学生"),
    TEACHER(1, "老师");

    private static final Log log = LogFactory.getLog(Role.class);

    /**
     * 值
     */
    @Getter
    private int value;

    /**
     * 描述
     */
    @Getter
    private String description;

    Role(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonCreator
    public static Role fromString(String roleStr) {
        try {
            Role role = Role.valueOf(roleStr);
            return role;
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    /**
     * 校验身份
     *
     * @param user
     */
    public static void checkRole(User user) {
        if (user.getRole() == null) {
            throw new AuthException(ResponseCode.AUTH_ROLE_LIMITED);
        }
    }

    /**
     * 校验老师身份
     *
     * @param user
     */
    public static void checkTeacherRole(User user) {
        if (user.getRole() == null || !Role.TEACHER.equals(user.getRole())) {
            throw new AuthException(ResponseCode.AUTH_TEACHER_LIMITED);
        }
    }

    /**
     * 校验学生身份
     *
     * @param user
     */
    public static void checkStudentRole(User user) {
        if (user.getRole() == null || !Role.STUDENT.equals(user.getRole())) {
            throw new AuthException(ResponseCode.AUTH_STUDENT_LIMITED);
        }
    }
}
