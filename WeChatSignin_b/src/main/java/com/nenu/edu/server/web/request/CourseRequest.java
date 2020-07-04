package com.nenu.edu.server.web.request;

import com.nenu.edu.server.po.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: Liang Jiayue
 * @Description:课程请求
 */
@Data
public class CourseRequest {

    /**
     * 编号
     */
    @NotNull(message = "课程编号不能为空",
            groups = {
                    Update.class,
                    Delete.class,
                    View.class,
                    ListStudent.class,
                    Quit.class
            })
    private Long id;

    /**
     * 授课老师
     */
    private User teacher;

    /**
     * 课程名称
     */
    @NotEmpty(message = "课程名称不能为空", groups = {Create.class, Update.class})
    private String name;

    /**
     * 课程代码
     */
    @NotEmpty(message = "课程代码不能为空", groups = {Create.class, Update.class})
    private String code;

    /**
     * 上课时间
     */
    @NotEmpty(message = "上课时间不能为空", groups = {Create.class, Update.class})
    private String time;

    /**
     * 上课地点
     */
    @NotEmpty(message = "上课地点不能为空", groups = {Create.class, Update.class})
    private String place;

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
     * 老师修改组
     */
    public interface Update {
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
     * 学生列表组
     */
    public interface ListStudent {
    }

    /**
     * 搜索组
     */
    public interface Search {
    }

    /**
     * 退出组
     */
    public interface Quit {
    }
}
