package com.nenu.edu.server.vo;

import com.nenu.edu.server.po.Call;
import lombok.Data;

/**
 * @Author: Lu Jingyuan
 * @Description:点名视图
 */
@Data
public class CallVo {

    /**
     * 编号，即点名编号
     */
    private Long id;

    /**
     * 点名
     */
    private Call call;

    /**
     * 学生总数
     */
    private Long studentSum;

    /**
     * 签到总数
     */
    private Long signSum;

}
