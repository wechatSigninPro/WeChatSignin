package com.nenu.edu.server.mapper;

import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.po.Student;
import com.nenu.edu.server.util.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:25 2020/7/4
 */
public class StudentMapperTest extends SpringBaseTest {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    protected Class getTargetClass() {
        return StudentMapperTest.class;
    }

    @Test
    public void getById() {
        Student student = studentMapper.getById(1L);
        log.info(JsonUtil.toJsonString(student));
    }

    @Test
    public void save() {
        Student student = new Student();
        student.setMajor("计算机科学与技术专业");
        student.setGrade("2014级");
        student.setClazz("04011405班");

        Long result = studentMapper.insert(student);
        log.info("id: " + student.getId());
        log.info("result: " + result);

//        student = studentMapper.getById(student.getId());
//        log.info(JsonUtil.toJsonString(student));
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setId(1L);
        student.setMajor("计算机科学与技术专业");
        student.setGrade("2015级");
        student.setClazz("04011407班");

        Long result = studentMapper.update(student);
        log.info("result: " + result);

        student = studentMapper.getById(student.getId());
        log.info(JsonUtil.toJsonString(student));
    }

    @Test
    public void deleteById() {
        Long result = studentMapper.deleteById(2L);
        log.info("result: " + result);
    }

}
