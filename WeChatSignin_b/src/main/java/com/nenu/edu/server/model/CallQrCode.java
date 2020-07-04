package com.nenu.edu.server.model;

import lombok.Data;

/**
 * @Author: Ying Guoqing
 * @Description:点名二维码
 */
@Data
public class CallQrCode {

    /**
     * 老师的用户编号
     */
    private Long teacherUserId;

    /**
     * 加密后的数据
     */
    private String encryptedData;

}