package com.nenu.edu.server.service.impl;

import com.nenu.edu.server.enumeration.Role;
import com.nenu.edu.server.exception.ParamValidException;
import com.nenu.edu.server.mapper.StudentMapper;
import com.nenu.edu.server.mapper.TeacherMapper;
import com.nenu.edu.server.mapper.UserMapper;
import com.nenu.edu.server.model.Token;
import com.nenu.edu.server.po.Student;
import com.nenu.edu.server.po.Teacher;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.property.WxProperties;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.UserService;
import com.nenu.edu.server.util.DaoUtil;
import com.nenu.edu.server.util.JwtUtil;
import com.nenu.edu.server.util.RestUtil;
import com.nenu.edu.server.util.StringUtil;
import com.nenu.edu.server.web.request.StudentRequest;
import com.nenu.edu.server.web.request.TeacherRequest;
import com.nenu.edu.server.web.request.UserRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author i@xiaofeig.cn
 * @date 下午2:23 2018/4/6
 */
@Service
public class UserServiceImpl extends BaseLogService implements UserService {

    @Autowired
    private WxProperties wxProperties;

    @Autowired
    private RestUtil restUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected Class getType() {
        return UserServiceImpl.class;
    }

    @Override
    public Token login(String code) {
        String url = StringUtil.formatString(
                wxProperties.getLoginUrl(),
                wxProperties.getAppId(),
                wxProperties.getAppSecret(),
                code
        );

        JsonNode respBody = restUtil.get(url);
        JsonNode openIdNode = respBody.get("openid");

        if (openIdNode == null) {
            log.info("登录失败：" + respBody.toString());
            throw new ParamValidException("code", "code校验失败");
        }

        String openId = openIdNode.asText();

        User user = userMapper.getByOpenId(openId);
        if (user == null) {
            user = new User();
            user.setOpenId(openId);
            DaoUtil.checkSingleRecordAccess(
                    userMapper.insert(user)
            );

        }

        return new Token(jwtUtil.generateToken(user));
    }

    @Override
    public void update(UserRequest userRequest, User user) {
        Role role = userRequest.getRole();

        switch (role) {
            case TEACHER:
                if (user.getTeacher() == null) {
                    Teacher teacher = new Teacher();
                    DaoUtil.checkSingleRecordAccess(
                            teacherMapper.insert(teacher)
                    );
                    user.setTeacher(teacher);
                }

                break;
            case STUDENT:
                if (user.getStudent() == null) {
                    Student student = new Student();
                    DaoUtil.checkSingleRecordAccess(
                            studentMapper.insert(student)
                    );
                    user.setStudent(student);
                }
                break;
            default:
                break;
        }
        user.setRole(role);
        user.setName(userRequest.getName());
        user.setSchool(userRequest.getSchool());
        user.setCampusId(userRequest.getCampusId());
        DaoUtil.checkSingleRecordAccess(
                userMapper.update(user)
        );
    }

    @Override
    public void updateMore(UserRequest userRequest, User user) {
        Role role = user.getRole();

        switch (role) {
            case TEACHER:
                break;
            case STUDENT:
                Student student = user.getStudent();
                student.setMajor(userRequest.getMajor());
                student.setGrade(userRequest.getGrade());
                student.setClazz(userRequest.getClazz());
                DaoUtil.checkSingleRecordAccess(
                        studentMapper.update(student)
                );
                break;
            default:
                break;
        }

        user.setDepartment(userRequest.getDepartment());
        user.setPhoneNum(userRequest.getPhoneNum());
        user.setEmail(userRequest.getEmail());

        DaoUtil.checkSingleRecordAccess(
                userMapper.updateMore(user)
        );
    }

    @Override
    public User getTeacherInfo(TeacherRequest request) {
        User user = userMapper.getByTeacherId(request.getId());
        if (user == null) {
            throw new ParamValidException("id", "老师编号有误");
        }

        return user;
    }

    @Override
    public User getStudentInfo(StudentRequest request) {
        User user = userMapper.getByStudentId(request.getId());
        if (user == null) {
            throw new ParamValidException("id", "学生编号有误");
        }

        return user;
    }

}
