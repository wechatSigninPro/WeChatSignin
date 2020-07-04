package com.nenu.edu.server.web.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: Liang Jiayue
 * @Description:反馈请求
 */
@Data
public class FeedbackRequest {

    /**
     * 内容
     */
    @NotEmpty(message = "内容不能为空", groups = {Create.class})
    private String content;

    /**
     * 创建组
     */
    public interface Create {
    }
}
