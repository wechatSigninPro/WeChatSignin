package com.nenu.edu.server.service;

import com.nenu.edu.server.po.Course;
import com.nenu.edu.server.po.StudentCourse;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.web.request.CourseRequest;

import java.util.List;

/**
 * 课程业务层
 *
 * @author i@xiaofeig.cn
 * @date 下午5:33 2018/4/11
 */
public interface CourseService {

    /**
     * 老师创建课程
     *
     * @param request
     * @param user
     * @return
     */
    Course create(CourseRequest request, User user);

    /**
     * 老师修改课程
     *
     * @param request
     * @return
     */
    Course update(CourseRequest request, User user);

    /**
     * 老师删除课程
     *
     * @param request
     * @return
     */
    void delete(CourseRequest request, User user);

    /**
     * 查询
     *
     * @param request
     * @return
     */
    Course view(CourseRequest request);

    /**
     * 课程列表
     *
     * @param user
     * @return
     */
    List<Course> listCourse(User user);

    /**
     * 学生列表
     *
     * @param request
     * @return
     */
    List<User> listStudent(CourseRequest request);

    /**
     * 搜索课程
     *
     * @param request
     * @param user
     * @return
     */
    List<Course> search(CourseRequest request, User user);

    /**
     * 搜索所有课程
     *
     * @param request
     * @param user
     * @return
     */
    List<StudentCourse> searchAll(CourseRequest request, User user);

    /**
     * 根据编号、学生编号查询
     *
     * @param request
     * @param user
     * @return
     */
    StudentCourse viewByIdAndStudent(CourseRequest request, User user);

    /**
     * 学生退出课程
     *
     * @param request
     * @param user
     * @return
     */
    StudentCourse quit(CourseRequest request, User user);
}
