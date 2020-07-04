package com.nenu.edu.server.enumeration;

import com.nenu.edu.server.BaseTest;
import com.nenu.edu.server.po.User;
import org.junit.Test;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:22 2020/7/4
 */
public class RoleTest extends BaseTest {

    @Override
    protected Class getTargetClass() {
        return null;
    }

    @Test
    public void testSwitch() {
        User user = new User();
        user.setRole(null);

        switch (user.getRole()) {
            case TEACHER:
                log.info("teacher");
                break;
            case STUDENT:
                log.info("student");
                break;
            default:
                log.info("null");
                break;
        }

    }
}