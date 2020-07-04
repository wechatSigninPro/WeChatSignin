package com.nenu.edu.server.web.controller;

import com.nenu.edu.server.annotation.CurrentUser;
import com.nenu.edu.server.po.CourseApplication;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.CourseApplicationService;
import com.nenu.edu.server.web.request.CourseApplyRequest;
import com.nenu.edu.server.web.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Ying Guoqing
 * @Description:课程申请控制层
 */
@RestController
@RequestMapping("/api/course/application")
public class CourseApplicationController {

    @Autowired
    private CourseApplicationService courseApplicationService;

    @PostMapping("/apply")
    public ResponseWrapper<CourseApplication> apply(@RequestBody @Validated(value = CourseApplyRequest.Apply.class) CourseApplyRequest request, @CurrentUser User user) {
        CourseApplication apply = courseApplicationService.apply(request, user);

        ResponseWrapper<CourseApplication> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(apply);

        return responseWrapper;
    }

    @PostMapping("/handle")
    public ResponseWrapper<CourseApplication> handle(@RequestBody @Validated(value = CourseApplyRequest.Handle.class) CourseApplyRequest request, @CurrentUser User user) {
        CourseApplication apply = courseApplicationService.handle(request, user);

        ResponseWrapper<CourseApplication> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(apply);

        return responseWrapper;
    }

    @GetMapping("/list")
    public ResponseWrapper<List<CourseApplication>> handle(@CurrentUser User user) {
        List<CourseApplication> courseApplications = courseApplicationService.list(user);

        ResponseWrapper<List<CourseApplication>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(courseApplications);

        return responseWrapper;
    }

    @PostMapping("/view")
    public ResponseWrapper<CourseApplication> view(@RequestBody @Validated(value = CourseApplyRequest.View.class) CourseApplyRequest request) {
        CourseApplication apply = courseApplicationService.view(request);

        ResponseWrapper<CourseApplication> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(apply);

        return responseWrapper;
    }

}
