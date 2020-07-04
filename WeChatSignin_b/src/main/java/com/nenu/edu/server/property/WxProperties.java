package com.nenu.edu.server.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Lu Jingyuan
 * @Description:微信配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    /**
     * 小程序编号
     */
    private String appId;

    /**
     * 小程序密钥
     */
    private String appSecret;

    /**
     * 登录凭证校验链接
     * 参考：https://developers.weixin.qq.com/miniprogram/dev/api/api-login.html#wxloginobject
     */
    private String loginUrl;
}
