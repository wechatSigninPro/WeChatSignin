package com.nenu.edu.server.po;

import lombok.Data;

/**
 * @Author: Liang Jiayue
 * @Description:反馈
 */
@Data
public class Feedback extends BasePo {

    /**
     * 用户
     */
    private User user;

    /**
     * 内容
     */
    private String content;

}
