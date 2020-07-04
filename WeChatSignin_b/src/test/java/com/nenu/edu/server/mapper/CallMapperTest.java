package com.nenu.edu.server.mapper;

import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.util.JsonUtil;
import com.nenu.edu.server.vo.CallVo;
import com.nenu.edu.server.vo.SignVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:23 2020/7/4
 */
public class CallMapperTest extends SpringBaseTest {

    @Autowired
    private CallMapper callMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    protected Class getTargetClass() {
        return CallMapperTest.class;
    }

    @Test
    public void getCallVoByTeacherId() {
        List<CallVo> callVos = callMapper.getCallVoByTeacherId(1L);
        log.info("size: " + callVos.size());
        log.info(JsonUtil.toJsonString(callVos));
    }

    @Test
    public void getSignVoByStudentId() {
        List<SignVo> signVos = callMapper.getSignVoByStudentId(10L);
        log.info("size: " + signVos.size());
        log.info(JsonUtil.toJsonString(signVos));
    }

    @Test
    public void getCallVoByCallId() {
        CallVo callVo = callMapper.getCallVoByCallId(7L);
        log.info(JsonUtil.toJsonString(callVo));
    }

    @Test
    public void getSignVoByCallIdAndStudentId() {
        SignVo signVo = callMapper.getSignVoByCallIdAndStudentId(5L, 12L);
        log.info(JsonUtil.toJsonString(signVo));
    }

    @Test
    public void searchCallVoByTeacherId() {
        List<CallVo> callVos = callMapper.searchCallVoByTeacherId(3L, "%C语言%");
        log.info("size: " + callVos.size());
        log.info(JsonUtil.toJsonString(callVos));

    }

    @Test
    public void searchSignVoByStudentId() {
        List<SignVo> signVos = callMapper.searchSignVoByStudentId(10L, "%");
        log.info(JsonUtil.toJsonString(signVos));
    }

    @Test
    public void getCallVoByCourseId() {
        List<CallVo> callVos = callMapper.getCallVoByCourseId(7L);
        log.info("size: " + callVos.size());
        log.info(JsonUtil.toJsonString(callVos));
    }

    @Test
    public void getSignVoByStudentIdAndCourseId() {
        List<SignVo> signVos = callMapper.getSignVoByStudentIdAndCourseId(10L, 7L);
        log.info("size: " + signVos.size());
        log.info(JsonUtil.toJsonString(signVos));
    }

    @Test
    public void getSignRecordVoByCallId() {
        List<SignRecordVo> signRecordVos = callMapper.getSignRecordVoByCallId(7L);
        log.info("size: " + signRecordVos.size());
        log.info(JsonUtil.toJsonString(signRecordVos));
    }

    @Test
    public void statistics() {
        Long courseId = 7L;

        Course course = courseMapper.getById(courseId);
        log.info("课程信息：");
        log.info(JsonUtil.toJsonString(course));

        List<CallVo> callVos = callMapper.getCallVoByCourseId(courseId);
        for (int i = 0; i < callVos.size(); i++) {
            CallVo callVo = callVos.get(i);
            log.info("-----------------------");
            log.info("第" + (i + 1) + "次点名：");
            log.info(JsonUtil.toJsonString(callVo));

            List<SignRecordVo> signRecordVos = callMapper.getSignRecordVoByCallId(callVo.getId());
            log.info("名单：");
            log.info(JsonUtil.toJsonString(signRecordVos));
        }

    }
}