package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Lu Jingyuan
 * @Description:学生数据映射
 */
@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 更新
     *
     * @param student
     * @return
     */
    Long update(Student student);

}
