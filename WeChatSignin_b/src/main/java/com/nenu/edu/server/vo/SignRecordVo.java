package com.nenu.edu.server.vo;

import com.nenu.edu.server.po.Sign;
import com.nenu.edu.server.po.User;
import lombok.Data;

/**
 * @Author: Lu Jingyuan
 * @Description:签到记录视图
 */
@Data
public class SignRecordVo {

    /**
     * 签到编号
     */
    private Long id;

    /**
     * 用户
     */
    private User user;

    /**
     * 签到
     */
    private Sign sign;
}
