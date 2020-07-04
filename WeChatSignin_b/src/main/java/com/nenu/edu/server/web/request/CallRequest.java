package com.nenu.edu.server.web.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: Liang Jiayue
 * @Description:点名请求
 */
@Data
public class CallRequest {

    /**
     * 编号
     */
    @NotNull(message = "点名编号不能为空", groups = {Delete.class, View.class, ListStudent.class, QrCode.class})
    private Long id;

    /**
     * 课程编号
     */
    @NotNull(message = "课程编号不能为空", groups = {Create.class, ListByCourse.class})
    private Long courseId;

    /**
     * 口令
     */
    @NotEmpty(message = "口令不能为空", groups = {Create.class, ListByPasswordAndLocation.class})
    private String password;

    /**
     * 坐标纬度
     */
    @NotNull(message = "坐标纬度不能为空", groups = {Create.class, ListByPasswordAndLocation.class})
    private Float latitude;

    /**
     * 坐标经度
     */
    @NotNull(message = "坐标经度不能为空", groups = {Create.class, ListByPasswordAndLocation.class})
    private Float longitude;

    /**
     * 搜索关键字
     */
    @NotNull(message = "搜索关键字不能为空", groups = {Search.class})
    private String keyword;

    /**
     * 老师创建组
     */
    public interface Create {
    }

    /**
     * 老师删除组
     */
    public interface Delete {
    }

    /**
     * 查询组
     */
    public interface View {
    }

    /**
     * 根据口令及位置查询组
     */
    public interface ListByPasswordAndLocation {
    }

    /**
     * 搜索组
     */
    public interface Search {
    }

    /**
     * 根据课程查询
     */
    public interface ListByCourse {
    }

    /**
     * 查询学生名单
     */
    public interface ListStudent {
    }

    /**
     * 点名二维码
     */
    public interface QrCode {
    }
}