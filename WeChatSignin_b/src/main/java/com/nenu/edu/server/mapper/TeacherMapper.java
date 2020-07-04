package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Lu Jingyuan
 * @Description:老师数据映射
 */
@Mapper
@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {
}