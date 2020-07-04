package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.Course;
import com.nenu.edu.server.po.StudentCourse;
import com.nenu.edu.server.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lu Jingyuan
 * @Description:学生选课
 */
@Mapper
@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    /**
     * 根据学生编号和课程编号查询
     *
     * @param studentId
     * @param courseId
     * @return
     */
    StudentCourse getByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    /**
     * 根据学生编号查询课程
     *
     * @param studentId
     * @return
     */
    List<Course> listCourseByStudentId(Long studentId);

    /**
     * 根据课程编号查询学生
     *
     * @param courseId
     * @return
     */
    List<User> listStudentByCourseId(Long courseId);

}
