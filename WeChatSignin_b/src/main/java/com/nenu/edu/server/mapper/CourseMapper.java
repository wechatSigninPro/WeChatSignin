package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.Course;
import com.nenu.edu.server.po.StudentCourse;
import com.nenu.edu.server.vo.ExcelCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lu Jingyuan
 * @Description:课程数据映射
 */
@Mapper
@Repository
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据老师编号查询课程
     *
     * @param teacherId
     * @return
     */
    List<Course> listByTeacherId(Long teacherId);

    /**
     * 修改课程
     *
     * @param course
     * @return
     */
    Long update(Course course);

    /**
     * 根据关键字搜索课程
     *
     * @param keyword
     * @return
     */
    List<Course> search(String keyword);

    /**
     * 根据关键字，老师编号搜索课程
     *
     * @param keyword
     * @param teacherId
     * @return
     */
    List<Course> searchByTeacher(@Param("keyword") String keyword, @Param("teacherId") Long teacherId);

    /**
     * 根据关键字，学生编号搜索课程
     *
     * @param keyword
     * @param studentId
     * @return
     */
    List<Course> searchByStudent(@Param("keyword") String keyword, @Param("studentId") Long studentId);

    /**
     * 根据关键字，学生编号搜索所有课程
     *
     * @param keyword
     * @param studentId
     * @return
     */
    List<StudentCourse> searchAllByStudent(@Param("keyword") String keyword, @Param("studentId") Long studentId);

    /**
     * 根据课程编号，学生编号查询课程
     *
     * @param id
     * @param studentId
     * @return
     */
    StudentCourse getByIdAndStudent(@Param("id") Long id, @Param("studentId") Long studentId);

    /**
     * 根据老师编号查询Excel课程
     *
     * @param teacherId
     * @return
     */
    List<ExcelCourse> listExcelCourseByTeacher(Long teacherId);
}
