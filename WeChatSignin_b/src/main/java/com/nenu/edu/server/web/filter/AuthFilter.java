package com.nenu.edu.server.web.filter;

import com.nenu.edu.server.enumeration.ResponseCode;
import com.nenu.edu.server.exception.AuthException;
import com.nenu.edu.server.property.JwtProperties;
import com.nenu.edu.server.service.BaseLogService;
import com.nenu.edu.server.util.JsonUtil;
import com.nenu.edu.server.util.JwtUtil;
import com.nenu.edu.server.web.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: Liang Jiayue
 * @Description:认证过滤器
 */
@Component
public class AuthFilter extends BaseLogService implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected Class getType() {
        return AuthFilter.class;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(jwtProperties.getHeader());

        try {
            if (authHeader == null || authHeader.isEmpty()) {
                throw new AuthException(ResponseCode.AUTH_FAILED_WITHOUT_TOKEN);
            } else if (!authHeader.startsWith(jwtProperties.getTokenHead())) {
                throw new AuthException(ResponseCode.AUTH_FAILED_TOKEN_ERROR);
            } else {
                final String token = authHeader.substring(jwtProperties.getTokenHead().length());
                request.setAttribute("userId", jwtUtil.getUserIdFromToken(token));
                return true;
            }
        } catch (AuthException e) {
            log.info(e.getMessage());
            ResponseWrapper responseWrapper = new ResponseWrapper(e.getResponseCode());
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JsonUtil.toJsonString(responseWrapper));
            printWriter.flush();
            printWriter.close();
            return false;
        }
    }

}