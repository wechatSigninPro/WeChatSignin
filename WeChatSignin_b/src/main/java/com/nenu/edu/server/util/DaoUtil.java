package com.nenu.edu.server.util;

import com.nenu.edu.server.exception.DataAccessException;

/**
 * @Author: Liang Jiayue
 * @Description:数据访问工具类
 */
public class DaoUtil {

    /**
     * 校验单条记录访问
     *
     * @param result
     */
    public static void checkSingleRecordAccess(Long result) {
        if (result != 1) {
            throw new DataAccessException();
        }
    }
}
