package com.nenu.edu.server.mapper;

import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.po.Course;
import com.nenu.edu.server.po.StudentCourse;
import com.nenu.edu.server.util.JsonUtil;
import com.nenu.edu.server.vo.ExcelCourse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:25 2020/7/4
 */
public class CourseMapperTest extends SpringBaseTest {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    protected Class getTargetClass() {
        return CourseMapperTest.class;
    }

    @Test
    public void getById() {
        Course course = courseMapper.getById(1L);
        log.info(JsonUtil.toJsonString(course));
    }


    @Test
    public void update() {
        Course course = courseMapper.getById(2L);
        course.setName("多媒体设计");
        Long result = courseMapper.update(course);

        log.info("result: " + result);
    }

    @Test
    public void search() {
        String keyword = "%小飞%";
        List<Course> courses = courseMapper.search(keyword);
        log.info(JsonUtil.toJsonString(courses));
    }

    @Test
    public void delete() {
        Long courseId = 4L;
        Long result = courseMapper.deleteById(courseId);
        log.info("result: " + result);
    }

    @Test
    public void searchAllByStudent() {
        List<StudentCourse> studentCourses = courseMapper.searchAllByStudent("%语文%", 14L);
        log.info(JsonUtil.toJsonString(studentCourses));
    }

    @Test
    public void getByIdAndStudent() {
        StudentCourse studentCourse = courseMapper.getByIdAndStudent(11L, 14L);
        log.info(JsonUtil.toJsonString(studentCourse));
    }

    @Test
    public void listExcelCourseByTeacher() {
        List<ExcelCourse> excelCourses = courseMapper.listExcelCourseByTeacher(4L);
        log.info("size: " + excelCourses.size());
        log.info(JsonUtil.toJsonString(excelCourses));
    }
}