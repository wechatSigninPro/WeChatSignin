package com.nenu.edu.server.other;

import com.fasterxml.jackson.databind.JsonNode;
import com.nenu.edu.server.SpringBaseTest;
import com.nenu.edu.server.property.WxProperties;
import com.nenu.edu.server.util.JsonUtil;
import com.nenu.edu.server.util.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:29 2020/7/4
 */
public class RestTest extends SpringBaseTest {

    @Autowired
    private WxProperties wxProperties;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @Override
    protected Class getTargetClass() {
        return RestTest.class;
    }


    @Test
    public void testGet() {
        String appId = wxProperties.getAppId();
        String appSecret = wxProperties.getAppSecret();
        String code = "061UdJU72wVAeR0sveT72u4IU72UdJUR";
        String url = StringUtil.formatString(wxProperties.getLoginUrl(), appId, appSecret, code);

        RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println(wxProperties.getAppId());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        JsonNode jsonNode = JsonUtil.toJsonNode(responseEntity.getBody());
        JsonNode openId = jsonNode.get("openid");
        if (openId == null) {
            log.info(null);
        } else {
            log.info("result: " + openId.toString());
        }
    }

}