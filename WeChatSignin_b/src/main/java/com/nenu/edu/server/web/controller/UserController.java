package com.nenu.edu.server.web.controller;

import com.nenu.edu.server.annotation.CurrentUser;
import com.nenu.edu.server.model.Token;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.UserService;
import com.nenu.edu.server.web.request.StudentRequest;
import com.nenu.edu.server.web.request.TeacherRequest;
import com.nenu.edu.server.web.request.UserRequest;
import com.nenu.edu.server.web.response.ResponseWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Ying Guoqing
 * @Description:用户控制层
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    protected final Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseWrapper<Token> login(@RequestBody @Validated(value = UserRequest.Login.class) UserRequest request) {
        Token token = userService.login(request.getCode());

        ResponseWrapper<Token> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(token);

        return responseWrapper;
    }

    @GetMapping("/info")
    public ResponseWrapper<User> getInfo(@CurrentUser User user) {
        ResponseWrapper<User> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(user);

        return responseWrapper;
    }

    @PostMapping("/info")
    public ResponseWrapper<User> updateInfo(@RequestBody @Validated(value = UserRequest.Update.class) UserRequest request, @CurrentUser User user) {
        userService.update(request, user);

        ResponseWrapper<User> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(user);

        return responseWrapper;
    }

    @PostMapping("/more")
    public ResponseWrapper<User> updateMore(@RequestBody UserRequest request, @CurrentUser User user) {
        userService.updateMore(request, user);

        ResponseWrapper<User> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(user);

        return responseWrapper;
    }

    @PostMapping("/teacher")
    public ResponseWrapper<User> getTeacherInfo(@RequestBody @Validated TeacherRequest request) {
        User user = userService.getTeacherInfo(request);

        ResponseWrapper<User> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(user);

        return responseWrapper;
    }

    @PostMapping("/student")
    public ResponseWrapper<User> getStudentInfo(@RequestBody @Validated StudentRequest request) {
        User user = userService.getStudentInfo(request);

        ResponseWrapper<User> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success(user);

        return responseWrapper;
    }
}