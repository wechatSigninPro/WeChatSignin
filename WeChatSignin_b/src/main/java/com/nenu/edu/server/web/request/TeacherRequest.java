package com.nenu.edu.server.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Liang Jiayue
 * @Description:老师请求
 */
@Data
public class TeacherRequest {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private Long id;
}
