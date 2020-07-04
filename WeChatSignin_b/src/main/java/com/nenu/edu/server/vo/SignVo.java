package com.nenu.edu.server.vo;

import com.nenu.edu.server.po.Sign;
import lombok.Data;

/**
 * @Author: Lu Jingyuan
 * @Description:签到视图
 */
@Data
public class SignVo {

    /**
     * 编号，即点名编号
     */
    private Long id;

    /**
     * 签到
     */
    private Sign sign;
}