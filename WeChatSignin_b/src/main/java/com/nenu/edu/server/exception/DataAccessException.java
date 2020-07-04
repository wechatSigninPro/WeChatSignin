package com.nenu.edu.server.exception;

import com.nenu.edu.server.enumeration.ResponseCode;
import lombok.Getter;

/**
 * @Author: Liang Jiayue
 * @Description:数据访问异常
 */
public class DataAccessException extends RuntimeException {

    @Getter
    private ResponseCode responseCode;

    public DataAccessException() {
        super(ResponseCode.DATA_ACCESS_FAIL.getMsg());
        this.responseCode = ResponseCode.DATA_ACCESS_FAIL;
    }

}