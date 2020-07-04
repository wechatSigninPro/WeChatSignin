package com.nenu.edu.server.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 基本日志服务
 *
 * @author i@xiaofeig.cn
 * @date 下午7:52 2018/4/10
 */
public abstract class BaseLogService {

    protected Log log = LogFactory.getLog(getType());

    /**
     * 获取子类类型
     *
     * @return
     */
    protected abstract Class getType();

}
