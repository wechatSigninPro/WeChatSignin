package com.nenu.edu.server.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Lu Jingyuan
 * @Description:Json web token配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * 请求头
     */
    private String header;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 过期时间（七天，秒）
     */
    private Long expired;

    /**
     * 口令头部
     */
    private String tokenHead;
}