package com.nenu.edu.server.web.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: Liang Jiayue
 * @Description:签到请求
 */
@Data
public class SignRequest {

    /**
     * 编号
     */
    private Long id;

    /**
     * 点名编号
     */
    @NotNull(message = "点名编号不能为空", groups = {Create.class})
    private Long callId;

    /**
     * 坐标纬度
     */
    @NotNull(message = "坐标纬度不能为空", groups = {Create.class})
    private Float latitude;

    /**
     * 坐标经度
     */
    @NotNull(message = "坐标纬度不能为空", groups = {Create.class})
    private Float longitude;

    /**
     * 老师的用户编号
     */
    @NotNull(message = "老师的用户编号不能为空", groups = {QrCode.class})
    private Long teacherUserId;

    /**
     * 二维码加密数据
     */
    @NotEmpty(message = "二维码加密数据不能为空", groups = {QrCode.class})
    private String encryptedData;

    /**
     * 学生创建组
     */
    public interface Create {
    }

    /**
     * 二维码签到
     */
    public interface QrCode {
    }

}