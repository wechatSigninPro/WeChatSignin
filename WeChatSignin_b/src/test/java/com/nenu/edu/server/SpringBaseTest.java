package com.nenu.edu.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:32 2020/7/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public abstract class SpringBaseTest extends AbstractJUnit4SpringContextTests {

    protected final Log log = LogFactory.getLog(getTargetClass());

    protected abstract Class getTargetClass();
}