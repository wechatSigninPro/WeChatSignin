package com.nenu.edu.server.mapper;

import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.enumeration.Role;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.util.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:27 2020/7/4
 */
public class UserMapperTest extends SpringBaseTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    protected Class getTargetClass() {
        return UserMapperTest.class;
    }

    @Test
    public void save() {
        User user = new User();
        user.setName("xiaofei");
        user.setOpenId("open_id_test");
        user.setRole(Role.STUDENT);

        userMapper.insert(user);
        log.info(JsonUtil.toJsonString(user));
    }

    @Test
    public void getById() {
        User user = userMapper.getById(1L);
        log.info(JsonUtil.toJsonString(user));
    }

    @Test
    public void mockData() {
        String openId = "oWYQm0XTaeTCysez1kQwKHcHEhqU";
        User user = userMapper.getById(1L);
        if (user != null) {
            user.setOpenId(openId);
            userMapper.update(user);
        } else {
            user = new User();
            user.setOpenId(openId);
            userMapper.insert(user);
        }

    }
}