package com.nenu.edu.server.web.handler;

import com.nenu.edu.server.enumeration.ResponseCode;
import com.nenu.edu.server.exception.AuthException;
import com.nenu.edu.server.exception.DataAccessException;
import com.nenu.edu.server.exception.DownloadException;
import com.nenu.edu.server.exception.ParamValidException;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.web.response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lu Jingyuan
 * @Description:控制层异常处理器
 */
@RestControllerAdvice
public class ControllerExceptionHandler extends BaseLogService {

    @Override
    protected Class getType() {
        return ControllerExceptionHandler.class;
    }

    /**
     * 处理spring参数校验失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseWrapper<Map<String, String>> handle(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> errors = new HashMap<>(16);
        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.paramError(errors);
        return responseWrapper;
    }

    /**
     * 处理参数无效情况
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ParamValidException.class)
    public ResponseWrapper<Map<String, String>> handleCodingRequestError(ParamValidException e) {
        ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.paramError(e.getErrors());
        return responseWrapper;
    }

    /**
     * 处理认证失败情况
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    public ResponseWrapper<Map<String, String>> handleCodingRequestError(AuthException e) {
        ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>(e.getResponseCode());
        return responseWrapper;
    }

    /**
     * 处理数据访问异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseWrapper<Map<String, String>> handleCodingRequestError(DataAccessException e) {
        ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>(e.getResponseCode());
        return responseWrapper;
    }

    /**
     * 处理下载异常异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DownloadException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseWrapper<String> handleDownloadException(DownloadException e) {
        log.error(e.getMessage());
        ResponseWrapper<String> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setData(e.getMessage());
        return responseWrapper;
    }

    /**
     * 处理数据访问异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseWrapper<Map<String, String>> handleException(Exception e) {
        log.error(e.getMessage());
        ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>(ResponseCode.INTERNAL_ERROR);
        return responseWrapper;
    }


}
