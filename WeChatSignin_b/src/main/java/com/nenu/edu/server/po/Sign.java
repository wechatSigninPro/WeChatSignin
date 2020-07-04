package com.nenu.edu.server.po;

import lombok.Data;

/**
 * @Author: Liang Jiayue
 * @Description:签到
 */
@Data
public class Sign extends BasePo {

    /**
     * 点名
     */
    private Call call;

    /**
     * 学生
     */
    private Student student;

}