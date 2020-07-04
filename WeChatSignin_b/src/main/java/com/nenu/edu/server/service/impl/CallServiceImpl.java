package com.nenu.edu.server.service.impl;

import com.nenu.edu.server.enumeration.Role;
import com.nenu.edu.server.exception.DownloadException;
import com.nenu.edu.server.exception.ParamValidException;
import com.nenu.edu.server.mapper.CallMapper;
import com.nenu.edu.server.mapper.CourseMapper;
import com.nenu.edu.server.model.CallQrCode;
import com.nenu.edu.server.po.Call;
import com.nenu.edu.server.po.Course;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.service.CallService;
import com.nenu.edu.server.util.AesUtil;
import com.nenu.edu.server.util.DaoUtil;
import com.nenu.edu.server.util.JsonUtil;
import com.nenu.edu.server.util.PoiUtil;
import com.nenu.edu.server.vo.ExcelCourse;
import com.nenu.edu.server.vo.SignRecordVo;
import com.nenu.edu.server.web.request.CallRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author i@xiaofeig.cn
 * @date 下午5:11 2018/4/14
 */
@Service
public class CallServiceImpl extends BaseLogService implements CallService {

    @Autowired
    private CallMapper callMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    protected Class getType() {
        return CallServiceImpl.class;
    }

    @Override
    public Call create(CallRequest request, User user) {
        Role.checkTeacherRole(user);
        Course course = courseMapper.getById(request.getCourseId());
        if (course == null) {
            throw new ParamValidException("courseId", "课程不存在或被删除");
        } else if (!course.getTeacher().getId().equals(user.getTeacher().getId())) {
            throw new ParamValidException("courseId", "无权限为该课程发起点名");
        }
        Call call = new Call();
        call.setCourse(course);
        call.setPassword(request.getPassword());
        call.setLatitude(request.getLatitude());
        call.setLongitude(request.getLongitude());

        DaoUtil.checkSingleRecordAccess(
                callMapper.insert(call)
        );

        return call;
    }

    @Override
    public void delete(CallRequest request, User user) {
        Role.checkTeacherRole(user);
        Call call = callMapper.getById(request.getId());
        if (call == null) {
            throw new ParamValidException("id", "点名不存在或被删除");
        }
        Course course = courseMapper.getById(call.getCourse().getId());
        if (!course.getTeacher().getId().equals(user.getTeacher().getId())) {
            throw new ParamValidException("courseId", "无权限删除该点名记录");
        }
        DaoUtil.checkSingleRecordAccess(
                callMapper.deleteById(call.getId())
        );
    }

    @Override
    public List<Call> listByPasswordAndLocation(CallRequest request, User user) {
        Role.checkStudentRole(user);
        return callMapper.listByPasswordAndStudentAndLocation(request.getPassword(), user.getStudent().getId(), request.getLatitude(), request.getLongitude());
    }

    @Override
    public List<?> list(User user) {
        Role.checkRole(user);

        switch (user.getRole()) {
            case TEACHER:
                return callMapper.getCallVoByTeacherId(user.getTeacher().getId());
            case STUDENT:
                return callMapper.getSignVoByStudentId(user.getStudent().getId());
            default:
                return null;
        }
    }

    @Override
    public Object view(CallRequest request, User user) {
        Role.checkRole(user);
        switch (user.getRole()) {
            case TEACHER:
                return callMapper.getCallVoByCallId(request.getId());
            case STUDENT:
                return callMapper.getSignVoByCallIdAndStudentId(request.getId(), user.getStudent().getId());
            default:
                return null;
        }
    }

    @Override
    public List<?> search(CallRequest request, User user) {
        Role.checkRole(user);
        String keyword = request.getKeyword();
        keyword = keyword.isEmpty() ? "%" : "%" + keyword + "%";
        switch (user.getRole()) {
            case TEACHER:
                return callMapper.searchCallVoByTeacherId(user.getTeacher().getId(), keyword);
            case STUDENT:
                return callMapper.searchSignVoByStudentId(user.getStudent().getId(), keyword);
            default:
                return null;
        }
    }

    @Override
    public List<?> listByCourse(CallRequest request, User user) {
        Role.checkRole(user);
        switch (user.getRole()) {
            case TEACHER:
                return callMapper.getCallVoByCourseId(request.getCourseId());
            case STUDENT:
                return callMapper.getSignVoByStudentIdAndCourseId(user.getStudent().getId(), request.getCourseId());
            default:
                return null;
        }
    }

    @Override
    public List<SignRecordVo> listStudent(CallRequest request, User user) {
        Role.checkRole(user);
        return callMapper.getSignRecordVoByCallId(request.getId());
    }

    @Override
    public String qrCode(CallRequest request, User user) {
        Role.checkTeacherRole(user);

        Call call = callMapper.getById(request.getId());
        if (call == null || !call.getCourse().getTeacher().getId().equals(user.getTeacher().getId())) {
            throw new ParamValidException("id", "点名不存在或已被删除");
        }
        CallQrCode qrCode = new CallQrCode();
        qrCode.setEncryptedData(AesUtil.encrypt(user.getOpenId(), call.getId().toString()));
        qrCode.setTeacherUserId(user.getId());

        return JsonUtil.toJsonString(qrCode);
    }

    @Override
    public byte[] exportToExcel(User user) {
        Role.checkTeacherRole(user);
        List<ExcelCourse> courses = courseMapper.listExcelCourseByTeacher(user.getTeacher().getId());
        if(courses == null || courses.size() == 0) {
            throw new DownloadException("导出失败，无签到数据");
        }
        try {
            return PoiUtil.generateExcel(courses);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParamValidException("user", "导出签到记录失败");
        }
    }


}
