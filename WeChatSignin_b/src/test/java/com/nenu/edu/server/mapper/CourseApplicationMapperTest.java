package com.nenu.edu.server.mapper;

import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.po.CourseApplication;
import com.nenu.edu.server.util.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:23 2020/7/4
 */
public class CourseApplicationMapperTest extends SpringBaseTest {

    @Autowired
    private CourseApplicationMapper courseApplicationMapper;

    @Override
    protected Class getTargetClass() {
        return CourseApplicationMapperTest.class;
    }

    @Test
    public void getById() {
        CourseApplication courseApplication = courseApplicationMapper.getById(1L);
        log.info(JsonUtil.toJsonString(courseApplication));
    }

}