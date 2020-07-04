package com.nenu.edu.server.service;

import com.nenu.edu.server.model.Token;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.web.request.StudentRequest;
import com.nenu.edu.server.web.request.TeacherRequest;
import com.nenu.edu.server.web.request.UserRequest;

/**
 * 用户业务层
 *
 * @author i@xiaofeig.cn
 * @date 下午2:21 2018/4/6
 */
public interface UserService {


    /**
     * 登录
     *
     * @param code
     * @return
     */
    Token login(String code);


    /**
     * 更新
     *
     * @param userRequest
     * @param user
     * @return
     */
    void update(UserRequest userRequest, User user);


    /**
     * 更新更多
     *
     * @param userRequest
     * @param user
     * @return
     */
    void updateMore(UserRequest userRequest, User user);

    /**
     * 老师信息
     *
     * @param request
     * @return
     */
    User getTeacherInfo(TeacherRequest request);

    /**
     * 学生信息
     *
     * @param request
     * @return
     */
    User getStudentInfo(StudentRequest request);
}
