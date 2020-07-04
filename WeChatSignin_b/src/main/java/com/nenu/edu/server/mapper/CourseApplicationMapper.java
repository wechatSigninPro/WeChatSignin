package com.nenu.edu.server.mapper;

import com.nenu.edu.server.enumeration.ApplyStatus;
import com.nenu.edu.server.po.CourseApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lu Jingyuan
 * @Description:课程申请数据映射
 */
@Mapper
@Repository
public interface CourseApplicationMapper extends BaseMapper<CourseApplication> {
    /**
     * 更新课程申请审核状态
     *
     * @param id
     * @param status
     * @return
     */
    Long updateStatus(@Param("id") Long id, @Param("status") ApplyStatus status);

    /**
     * 根据学生编号、课程编号及审核状态查找
     *
     * @param studentId
     * @param courseId
     * @param status
     * @return
     */
    CourseApplication getByStudentIdAndCourseIdAndStatus(@Param("studentId") Long studentId, @Param("courseId") Long courseId, @Param("status") ApplyStatus status);

    /**
     * 根据老师编号查找
     *
     * @param teacherId
     * @return
     */
    List<CourseApplication> getByTeacherId(Long teacherId);

    /**
     * 根据学生编号查找
     *
     * @param studentId
     * @return
     */
    List<CourseApplication> getByStudentId(Long studentId);

}