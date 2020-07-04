package com.nenu.edu.server.service.impl;

import com.nenu.edu.server.enumeration.ApplyStatus;
import com.nenu.edu.server.enumeration.Role;
import com.nenu.edu.server.exception.ParamValidException;
import com.nenu.edu.server.mapper.CourseApplicationMapper;
import com.nenu.edu.server.mapper.CourseMapper;
import com.nenu.edu.server.mapper.StudentCourseMapper;
import com.nenu.edu.server.po.*;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.CourseApplicationService;
import com.nenu.edu.server.util.DaoUtil;
import com.nenu.edu.server.web.request.CourseApplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author i@xiaofeig.cn
 * @date 下午8:52 2018/4/13
 */
@Service
public class CourseApplicationServiceImpl extends BaseLogService implements CourseApplicationService {


    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseApplicationMapper courseApplicationMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    protected Class getType() {
        return CourseApplicationServiceImpl.class;
    }

    @Override
    public CourseApplication apply(CourseApplyRequest request, User user) {
        Role.checkStudentRole(user);

        Course course = courseMapper.getById(request.getCourseId());
        if (course == null) {
            throw new ParamValidException("id", "课程不存在或已被删除");
        }
        Student student = user.getStudent();
        StudentCourse studentCourse = studentCourseMapper.getByStudentIdAndCourseId(student.getId(), course.getId());
        if (studentCourse != null) {
            throw new ParamValidException("id", "已加入该课程，无需重复申请");
        }
        CourseApplication courseApplication = courseApplicationMapper.getByStudentIdAndCourseIdAndStatus(
                student.getId(),
                course.getId(),
                ApplyStatus.WAITING
        );
        if (courseApplication != null) {
            throw new ParamValidException("id", "已完成申请，无需重复申请");
        }
        courseApplication = new CourseApplication();
        courseApplication.setCourse(course);
        courseApplication.setStudent(user.getStudent());
        courseApplication.setStatus(ApplyStatus.WAITING);

        DaoUtil.checkSingleRecordAccess(
                courseApplicationMapper.insert(courseApplication)
        );

        return courseApplication;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseApplication handle(CourseApplyRequest request, User user) {
        Role.checkTeacherRole(user);

        Map<String, String> errors = new HashMap<>(16);
        ApplyStatus status = request.getStatus();
        if (status.equals(ApplyStatus.WAITING)) {
            errors.put("status", "审核状态有误");
        }
        CourseApplication courseApplication = courseApplicationMapper.getById(request.getId());

        if (courseApplication == null) {
            errors.put("id", "选课申请不存在");
        } else if (!courseApplication.getStatus().equals(ApplyStatus.WAITING)) {
            errors.put("id", "选课申请已被审核");
        } else if (courseApplication.getCourse() == null) {
            errors.put("courseId", "课程不存在或已被删除");
        } else if (!courseApplication.getCourse().getTeacher().getId().equals(user.getTeacher().getId())) {
            errors.put("id", "无权限审核该选课申请");
        }
        if (errors.size() > 0) {
            throw new ParamValidException(errors);
        }

        courseApplication.setStatus(status);
        DaoUtil.checkSingleRecordAccess(
                courseApplicationMapper.updateStatus(courseApplication.getId(), status)
        );

        if (status.equals(ApplyStatus.PASSED)) {
            Student student = courseApplication.getStudent();
            Course course = courseApplication.getCourse();

            StudentCourse studentCourse = studentCourseMapper.getByStudentIdAndCourseId(student.getId(), course.getId());
            if (studentCourse == null) {
                studentCourse = new StudentCourse();
                studentCourse.setStudent(student);
                studentCourse.setCourse(course);
                DaoUtil.checkSingleRecordAccess(
                        studentCourseMapper.insert(studentCourse)
                );
            }
        }

        return courseApplication;
    }

    @Override
    public List<CourseApplication> list(User user) {
        Role role = user.getRole();
        List<CourseApplication> courseApplications;
        if(role != null) {
            switch (role) {
                case STUDENT:
                    courseApplications = courseApplicationMapper.getByStudentId(user.getStudent().getId());
                    break;
                case TEACHER:
                    courseApplications = courseApplicationMapper.getByTeacherId(user.getTeacher().getId());
                    break;
                default:
                    courseApplications = Collections.emptyList();
                    break;
            }
        } else {
            courseApplications = Collections.emptyList();
        }

        return courseApplications;
    }

    @Override
    public CourseApplication view(CourseApplyRequest request) {
        return courseApplicationMapper.getById(request.getId());
    }

}
