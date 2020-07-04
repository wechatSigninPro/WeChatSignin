package com.nenu.edu.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:32 2020/7/4
 */
public abstract class BaseTest {

    protected final Log log = LogFactory.getLog(getTargetClass());

    protected abstract Class getTargetClass();
}