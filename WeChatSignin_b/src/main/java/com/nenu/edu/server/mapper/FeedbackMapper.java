package com.nenu.edu.server.mapper;

import com.nenu.edu.server.po.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Lu Jingyuan
 * @Description:反馈数据映射
 */
@Mapper
@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {

}
