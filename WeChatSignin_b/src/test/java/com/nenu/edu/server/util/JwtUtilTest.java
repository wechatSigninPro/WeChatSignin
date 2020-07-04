package com.nenu.edu.server.util;

import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.po.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:31 2020/7/4
 */
public class JwtUtilTest extends SpringBaseTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected Class getTargetClass() {
        return JwtUtilTest.class;
    }

    @Test
    public void printToken() {
        log.info("token: " + generateToken());
    }

    @Test
    public void getUserIdFromToken() {
        String token = generateToken();
        Long userId = jwtUtil.getUserIdFromToken(token);
        log.info("userId: "+userId);
    }

    private String generateToken() {
        User user = new User();
        user.setId(74L);
        return jwtUtil.generateToken(user);
    }


}
