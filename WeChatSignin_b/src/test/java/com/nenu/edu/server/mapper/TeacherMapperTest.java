package com.nenu.edu.server.mapper;

import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.po.Teacher;
import com.nenu.edu.server.util.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:25 2020/7/4
 */
public class TeacherMapperTest extends SpringBaseTest {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    protected Class getTargetClass() {
        return TeacherMapperTest.class;
    }

    @Test
    public void getById() {
        Teacher teacher = teacherMapper.getById(3L);
        log.info(JsonUtil.toJsonString(teacher));
    }
}