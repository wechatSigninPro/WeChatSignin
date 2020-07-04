package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Lu Jingyuan
 * @Description:用户数据映射
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据微信用户编号查找
     *
     * @param openId
     * @return
     */
    User getByOpenId(String openId);

    /**
     * 更新
     *
     * @param user
     * @return
     */
    Long update(User user);

    /**
     * 更新更多
     *
     * @param user
     * @return
     */
    Long updateMore(User user);

    /**
     * 根据老师编号查找
     *
     * @param teacherId
     * @return
     */
    User getByTeacherId(Long teacherId);

    /**
     * 根据学生编号查找
     *
     * @param studentId
     * @return
     */
    User getByStudentId(Long studentId);
}
