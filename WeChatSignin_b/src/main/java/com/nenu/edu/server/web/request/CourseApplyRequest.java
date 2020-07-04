package com.nenu.edu.server.web.request;

import com.nenu.edu.server.enumeration.ApplyStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Liang Jiayue
 * @Description:课程申请请求
 */
@Data
public class CourseApplyRequest {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空", groups = {CourseApplyRequest.Handle.class, CourseApplyRequest.View.class})
    private Long id;
    /**
     * 课程编号
     */
    @NotNull(message = "课程编号不能为空", groups = {CourseApplyRequest.Apply.class})
    private Long courseId;

    /**
     * 申请状态
     */
    @NotNull(message = "申请状态不能为空", groups = {CourseApplyRequest.Handle.class})
    private ApplyStatus status;

    /**
     * 学生申请组
     */
    public interface Apply {
    }

    /**
     * 老师审核组
     */
    public interface Handle {
    }


    /**
     * 查询组
     */
    public interface View {
    }
}