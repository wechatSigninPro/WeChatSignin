package com.nenu.edu.server.web.response;

import com.nenu.edu.server.enumeration.ResponseCode;
import lombok.Data;

/**
 * @Author: Ying Guoqing
 * @Description:响应包装类
 */
@Data
public class ResponseWrapper<T> {

    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public ResponseWrapper() {
    }

    public ResponseWrapper(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public ResponseWrapper(ResponseCode responseCode, T data) {
        this(responseCode);
        this.data = data;
        this.code = ResponseCode.PARAM_ERROR.getCode();
    }

    /**
     * 参数错误
     *
     * @return
     */
    public void paramError(T data) {
        this.code = ResponseCode.PARAM_ERROR.getCode();
        this.msg = ResponseCode.PARAM_ERROR.getMsg();
        this.data = data;
    }

    /**
     * 请求成功
     *
     * @return
     */
    public void success(T data) {
        this.code = ResponseCode.SUCCESS.getCode();
        this.data = data;
    }

    /**
     * 系统异常
     *
     * @return
     */
    public void internalError(T data) {
        this.code = ResponseCode.INTERNAL_ERROR.getCode();
        this.msg = ResponseCode.INTERNAL_ERROR.getMsg();
        this.data = data;
    }

}