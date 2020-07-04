package com.nenu.edu.server.enumeration;

/**
 * @Author: Ying Guoqing
 * @Description:课程申请审核状态
 */
public enum ApplyStatus {

    WAITING("待审核"),
    PASSED("审核通过"),
    NOT_PASSED("审核未通过");

    /**
     * 描述
     */
    private String description;

    ApplyStatus(String description) {
        this.description = description;
    }
}