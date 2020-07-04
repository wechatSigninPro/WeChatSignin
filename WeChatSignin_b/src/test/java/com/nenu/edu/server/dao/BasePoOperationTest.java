package com.nenu.edu.server.dao;

import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.util.JsonUtil;
import org.junit.Test;

/**
 * @Author: Liang Jiayue
 */
public class BasePoOperationTest extends SpringBaseTest {

//    @Autowired
//    private UserOperation userOperation;

    @Override
    protected Class getTargetClass() {
        return BasePoOperationTest.class;
    }

    @Test
    public void save() {
        User user = new User();
        user.setOpenId("1234567");
//        userOperation.insert(user);
        log.info("user: " + JsonUtil.toJsonString(user));
    }
}