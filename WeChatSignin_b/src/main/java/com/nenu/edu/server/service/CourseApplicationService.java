package com.nenu.edu.server.service;

import com.nenu.edu.server.po.CourseApplication;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.web.request.CourseApplyRequest;

import java.util.List;

/**
 * 课程申请业务层
 *
 * @author i@xiaofeig.cn
 * @date 下午8:50 2018/4/13
 */
public interface CourseApplicationService {

    /**
     * 学生申请课程
     *
     * @param request
     * @param user
     * @return
     */
    CourseApplication apply(CourseApplyRequest request, User user);

    /**
     * 老师审核课程
     *
     * @param request
     * @param user
     * @return
     */
    CourseApplication handle(CourseApplyRequest request, User user);

    /**
     * 列表
     *
     * @param user
     * @return
     */
    List<CourseApplication> list(User user);

    /**
     * 查询课程
     *
     * @param request
     * @return
     */
    CourseApplication view(CourseApplyRequest request);
}
