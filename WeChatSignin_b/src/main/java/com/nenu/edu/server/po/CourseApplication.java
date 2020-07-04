package com.nenu.edu.server.po;

import com.nenu.edu.server.enumeration.ApplyStatus;
import lombok.Data;

/**
 * @Author: Liang Jiayue
 * @Description:课程申请
 */
@Data
public class CourseApplication extends BasePo {

    /**
     * 申请人
     */
    private Student student;

    /**
     * 课程
     */
    private Course course;

    /**
     * 审核状态
     */
    private ApplyStatus status;

}
