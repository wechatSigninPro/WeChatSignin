package com.nenu.edu.server.exception;

import com.nenu.edu.server.enumeration.ResponseCode;
import lombok.Getter;

/**
 * @Author: Liang Jiayue
 * @Description:认证异常
 */
public class AuthException extends RuntimeException {

    @Getter
    private ResponseCode responseCode;

    public AuthException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.responseCode = responseCode;
    }

}