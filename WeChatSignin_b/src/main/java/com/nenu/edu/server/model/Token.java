package com.nenu.edu.server.model;

import lombok.Data;

/**
 * @Author: Ying Guoqing
 * @Description:口令响应
 */
@Data
public class Token {
    /**
     * 口令
     */
    private String token;

    public Token(String token) {
        this.token = token;
    }
}
