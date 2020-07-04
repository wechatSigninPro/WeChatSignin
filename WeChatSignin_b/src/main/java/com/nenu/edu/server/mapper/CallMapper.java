package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.Call;
import com.nenu.edu.server.vo.CallVo;
import com.nenu.edu.server.vo.SignRecordVo;
import com.nenu.edu.server.vo.SignVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lu Jingyuan
 * @Description:点名数据映射
 */
@Mapper
@Repository
public interface CallMapper extends BaseMapper<Call> {

    /**
     * 根据口令查找5分钟内、50米内的点名
     *
     * @param password
     * @param studentId
     * @param latitude
     * @param longitude
     * @return
     */
    List<Call> listByPasswordAndStudentAndLocation(
            @Param("password") String password,
            @Param("studentId") Long studentId,
            @Param("latitude") Float latitude,
            @Param("longitude") Float longitude
    );

    /**
     * 查找5分钟内、50米内的点名
     *
     * @param id
     * @param latitude
     * @param longitude
     * @return
     */
    Call getByIdAndLocation(
            @Param("id") Long id,
            @Param("latitude") Float latitude,
            @Param("longitude") Float longitude
    );

    /**
     * 根据老师编号查找点名视图
     *
     * @param teacherId
     * @return
     */
    List<CallVo> getCallVoByTeacherId(Long teacherId);

    /**
     * 根据学生编号查找签到视图
     *
     * @param studentId
     * @return
     */
    List<SignVo> getSignVoByStudentId(Long studentId);

    /**
     * 根据点名编号查找点名视图
     *
     * @param callId
     * @return
     */
    CallVo getCallVoByCallId(Long callId);

    /**
     * 根据点名编号、学生编号查找签到视图
     *
     * @param callId
     * @param studentId
     * @return
     */
    SignVo getSignVoByCallIdAndStudentId(@Param("callId") Long callId, @Param("studentId") Long studentId);


    /**
     * 根据老师编号搜索点名视图
     *
     * @param teacherId
     * @param keyword
     * @return
     */
    List<CallVo> searchCallVoByTeacherId(@Param("teacherId") Long teacherId, @Param("keyword") String keyword);


    /**
     * 根据点名编号、学生编号搜索签到视图
     *
     * @param studentId
     * @param keyword
     * @return
     */
    List<SignVo> searchSignVoByStudentId(@Param("studentId") Long studentId, @Param("keyword") String keyword);

    /**
     * 根据课程编号查找点名视图
     *
     * @param courseId
     * @return
     */
    List<CallVo> getCallVoByCourseId(Long courseId);

    /**
     * 根据学生编号、课程编号查找签到视图
     *
     * @param studentId
     * @param courseId
     * @return
     */
    List<SignVo> getSignVoByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);


    /**
     * 根据点名编号查询签到记录视图
     *
     * @param callId
     * @return
     */
    List<SignRecordVo> getSignRecordVoByCallId(@Param("callId") Long callId);
}