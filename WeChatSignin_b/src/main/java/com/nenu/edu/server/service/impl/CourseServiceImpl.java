package com.nenu.edu.server.service.impl;

import com.nenu.edu.server.enumeration.Role;
import com.nenu.edu.server.exception.ParamValidException;
import com.nenu.edu.server.mapper.CourseMapper;
import com.nenu.edu.server.mapper.StudentCourseMapper;
import com.nenu.edu.server.po.Course;
import com.nenu.edu.server.po.StudentCourse;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.CourseService;
import com.nenu.edu.server.util.DaoUtil;
import com.nenu.edu.server.web.request.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author i@xiaofeig.cn
 * @date 下午5:34 2018/4/11
 */
@Service
public class CourseServiceImpl extends BaseLogService implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    protected Class getType() {
        return CourseServiceImpl.class;
    }

    @Override
    public Course create(CourseRequest request, User user) {
        Role.checkTeacherRole(user);

        Course course = new Course();
        course.setTeacher(user.getTeacher());
        course.setName(request.getName());
        course.setCode(request.getCode());
        course.setTime(request.getTime());
        course.setPlace(request.getPlace());

        DaoUtil.checkSingleRecordAccess(
                courseMapper.insert(course)
        );

        return course;
    }

    @Override
    public Course update(CourseRequest request, User user) {
        Role.checkTeacherRole(user);
        Course course = courseMapper.getById(request.getId());
        if (course == null) {
            throw new ParamValidException("id", "课程不存在或被删除");
        } else if (!course.getTeacher().getId().equals(user.getTeacher().getId())) {
            throw new ParamValidException("id", "无权限修改该课程");
        }
        course.setName(request.getName());
        course.setCode(request.getCode());
        course.setTime(request.getTime());
        course.setPlace(request.getPlace());

        DaoUtil.checkSingleRecordAccess(
                courseMapper.update(course)
        );

        return course;
    }

    @Override
    public void delete(CourseRequest request, User user) {
        Role.checkTeacherRole(user);
        Course course = courseMapper.getById(request.getId());
        if (course == null) {
            throw new ParamValidException("id", "课程不存在或被删除");
        } else if (!course.getTeacher().getId().equals(user.getTeacher().getId())) {
            throw new ParamValidException("id", "无权限删除该课程");
        }

        DaoUtil.checkSingleRecordAccess(
                courseMapper.deleteById(course.getId())
        );
    }

    @Override
    public Course view(CourseRequest request) {
        Course course = courseMapper.getById(request.getId());
        if (course == null) {
            throw new ParamValidException("id", "课程不存在或已被删除");
        }
        return course;
    }

    @Override
    public List<Course> listCourse(User user) {
        Role role = user.getRole();
        if (Role.TEACHER.equals(role)) {
            return courseMapper.listByTeacherId(user.getTeacher().getId());
        } else if (Role.STUDENT.equals(role)) {
            return studentCourseMapper.listCourseByStudentId(user.getStudent().getId());
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> listStudent(CourseRequest request) {
        return studentCourseMapper.listStudentByCourseId(request.getId());
    }

    @Override
    public List<Course> search(CourseRequest request, User user) {
        Role.checkRole(user);
        String keyword = request.getKeyword();
        keyword = keyword.isEmpty() ? "%" : "%" + keyword + "%";
        switch (user.getRole()) {
            case STUDENT:
                return courseMapper.searchByStudent(keyword, user.getStudent().getId());
            case TEACHER:
                return courseMapper.searchByTeacher(keyword, user.getTeacher().getId());
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public List<StudentCourse> searchAll(CourseRequest request, User user) {
        Role.checkStudentRole(user);
        String keyword = request.getKeyword();
        keyword = keyword.isEmpty() ? "%" : "%" + keyword + "%";
        return courseMapper.searchAllByStudent(keyword, user.getStudent().getId());
    }

    @Override
    public StudentCourse viewByIdAndStudent(CourseRequest request, User user) {
        Role.checkStudentRole(user);
        return courseMapper.getByIdAndStudent(request.getId(), user.getStudent().getId());
    }

    @Override
    public StudentCourse quit(CourseRequest request, User user) {
        Role.checkStudentRole(user);
        StudentCourse studentCourse = studentCourseMapper.getByStudentIdAndCourseId(user.getStudent().getId(), request.getId());
        if (studentCourse == null) {
            throw new ParamValidException("id", "已退出或未添加该课程");
        }
        DaoUtil.checkSingleRecordAccess(
                studentCourseMapper.deleteById(studentCourse.getId())
        );
        return studentCourse;
    }


}
