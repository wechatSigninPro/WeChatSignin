package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Lu Jingyuan
 * @Description:签到数据映射
 */
@Mapper
@Repository
public interface SignMapper extends BaseMapper<Sign> {

    /**
     * 根据点名编号和学生编号查询
     *
     * @param callId
     * @param studentId
     * @return
     */
    Sign getByCallIdAndStudentId(@Param("callId") Long callId, @Param("studentId") Long studentId);
}