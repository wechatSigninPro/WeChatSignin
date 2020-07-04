package com.nenu.edu.server.web.controller;

import com.nenu.edu.server.annotation.CurrentUser;
import com.nenu.edu.server.enumeration.Role;
import com.nenu.edu.server.po.Course;
import com.nenu.edu.server.po.StudentCourse;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.CourseService;
import com.nenu.edu.server.web.request.CourseRequest;
import com.nenu.edu.server.web.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程控制层
 *
 * @author i@xiaofeig.cn
 * @date 下午5:48 2018/4/11
 */
@RestController
@RequestMapping("/api/course")
public class CourseController extends BaseLogService {

    @Autowired
    private CourseService courseService;

    @Override
    protected Class getType() {
        return CourseController.class;
    }

    @PostMapping("/create")
    public ResponseWrapper<Course> create(@RequestBody @Validated(value = CourseRequest.Create.class) CourseRequest request, @CurrentUser User user) {
        Course course = courseService.create(request, user);

        ResponseWrapper<Course> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(course);

        return responseWrapper;
    }

    @PostMapping("/update")
    public ResponseWrapper<Course> update(@RequestBody @Validated(value = CourseRequest.Update.class) CourseRequest request, @CurrentUser User user) {
        Course course = courseService.update(request, user);

        ResponseWrapper<Course> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(course);

        return responseWrapper;
    }

    @PostMapping("/delete")
    public ResponseWrapper<?> delete(@RequestBody @Validated(value = CourseRequest.Delete.class) CourseRequest request, @CurrentUser User user) {
        courseService.delete(request, user);

        ResponseWrapper<Course> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(null);

        return responseWrapper;
    }

    @PostMapping("/view")
    public ResponseWrapper<Object> detail(@RequestBody @Validated(value = CourseRequest.View.class) CourseRequest request, @CurrentUser User user) {
        Role.checkRole(user);
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>();

        switch (user.getRole()) {
            case TEACHER:
                responseWrapper.success(courseService.view(request));
                break;
            case STUDENT:
                responseWrapper.success(courseService.viewByIdAndStudent(request, user));
                break;
            default:
                break;
        }
        return responseWrapper;
    }

    @GetMapping("/list/course")
    public ResponseWrapper<List<Course>> list(@CurrentUser User user) {
        List<Course> courses = courseService.listCourse(user);

        ResponseWrapper<List<Course>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(courses);

        return responseWrapper;
    }

    @PostMapping("/list/student")
    public ResponseWrapper<List<User>> list(@RequestBody @Validated(value = CourseRequest.ListStudent.class) CourseRequest request) {
        List<User> users = courseService.listStudent(request);

        ResponseWrapper<List<User>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(users);

        return responseWrapper;
    }

    @PostMapping("/search")
    public ResponseWrapper<List<Course>> search(@RequestBody @Validated(value = CourseRequest.Search.class) CourseRequest request, @CurrentUser User user) {
        List<Course> courses = courseService.search(request, user);

        ResponseWrapper<List<Course>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(courses);

        return responseWrapper;
    }

    @PostMapping("/search/all")
    public ResponseWrapper<List<StudentCourse>> searchAll(@RequestBody @Validated(value = CourseRequest.Search.class) CourseRequest request, @CurrentUser User user) {
        List<StudentCourse> courses = courseService.searchAll(request, user);

        ResponseWrapper<List<StudentCourse>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(courses);

        return responseWrapper;
    }

    @PostMapping("/quit")
    public ResponseWrapper<StudentCourse> quit(@RequestBody @Validated(value = CourseRequest.Quit.class) CourseRequest request, @CurrentUser User user) {
        StudentCourse studentCourse = courseService.quit(request, user);

        ResponseWrapper<StudentCourse> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(studentCourse);

        return responseWrapper;
    }
}
