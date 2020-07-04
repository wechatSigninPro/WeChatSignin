package com.nenu.edu.server.service.impl;

import com.nenu.edu.server.enumeration.Role;
import com.nenu.edu.server.exception.ParamValidException;
import com.nenu.edu.server.mapper.CallMapper;
import com.nenu.edu.server.mapper.SignMapper;
import com.nenu.edu.server.mapper.StudentCourseMapper;
import com.nenu.edu.server.mapper.UserMapper;
import com.nenu.edu.server.po.Call;
import com.nenu.edu.server.po.Sign;
import com.nenu.edu.server.po.StudentCourse;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.SignService;
import com.nenu.edu.server.util.AesUtil;
import com.nenu.edu.server.util.DaoUtil;
import com.nenu.edu.server.web.request.SignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author i@xiaofeig.cn
 * @date 下午8:06 2018/4/14
 */
@Service
public class SignServiceImpl extends BaseLogService implements SignService {

    @Autowired
    private CallMapper callMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    protected Class getType() {
        return SignServiceImpl.class;
    }

    @Override
    public Sign create(SignRequest signRequest, User user) {
        Role.checkStudentRole(user);
        Sign sign = signMapper.getByCallIdAndStudentId(signRequest.getCallId(), user.getStudent().getId());
        if (sign != null) {
            throw new ParamValidException("callId", "已完成签到，无需重复签到");
        }

        Call call = callMapper.getByIdAndLocation(
                signRequest.getCallId(),
                signRequest.getLatitude(),
                signRequest.getLongitude()
        );
        if (call == null) {
            throw new ParamValidException("callId", "签到失败，距离太远或点名时间已超过五分钟");
        }

        StudentCourse studentCourse = studentCourseMapper.getByStudentIdAndCourseId(user.getStudent().getId(), call.getCourse().getId());
        if (studentCourse == null) {
            throw new ParamValidException("callId", "签到失败，未申请该课程");
        }

        sign = new Sign();
        sign.setCall(call);
        sign.setStudent(user.getStudent());

        DaoUtil.checkSingleRecordAccess(
                signMapper.insert(sign)
        );

        return sign;
    }

    @Override
    public Sign qrcode(SignRequest signRequest, User user) {
        Role.checkStudentRole(user);

        User teacher = userMapper.getById(signRequest.getTeacherUserId());
        if (teacher == null) {
            throw new ParamValidException("teacherUserId", "老师的用户编号错误");
        }
        Long callId = -1L;
        try {
            String encryptedData = AesUtil.decrypt(teacher.getOpenId(), signRequest.getEncryptedData());
            callId = Long.valueOf(encryptedData);
        } catch (Exception e) {
            throw new ParamValidException("encryptedData", "二维码加密数据校验失败");
        }

        Call call = callMapper.getById(callId);
        if (call == null) {
            throw new ParamValidException("callId", "点名不存在或已被删除");
        }

        Sign sign = signMapper.getByCallIdAndStudentId(callId, user.getStudent().getId());
        if (sign != null) {
            throw new ParamValidException("callId", "已完成签到，无需重复签到");
        }

        StudentCourse studentCourse = studentCourseMapper.getByStudentIdAndCourseId(user.getStudent().getId(), call.getCourse().getId());
        if (studentCourse == null) {
            throw new ParamValidException("callId", "签到失败，未申请该课程");
        }

        sign = new Sign();
        sign.setCall(call);
        sign.setStudent(user.getStudent());

        DaoUtil.checkSingleRecordAccess(
                signMapper.insert(sign)
        );

        return sign;
    }


}
