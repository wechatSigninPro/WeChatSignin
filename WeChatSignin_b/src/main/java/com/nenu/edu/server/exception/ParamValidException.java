package com.nenu.edu.server.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Liang Jiayue
 * @Description:参数校验异常
 */
@Data
public class ParamValidException extends RuntimeException {

    /**
     * 参数校验结果
     */
    private Map<String, String> errors;

    public ParamValidException(Map<String, String> errors) {
        this.errors = errors;
    }

    public ParamValidException(String param, String msg) {
        errors = new HashMap<>(16);
        errors.put(param, msg);
    }

    /**
     * 添加参数异常信息
     *
     * @param param
     * @param msg
     * @return
     */
    public ParamValidException put(String param, String msg) {
        if (errors == null) {
            errors = new HashMap<>(16);
        }
        errors.put(param, msg);
        return this;
    }

}
