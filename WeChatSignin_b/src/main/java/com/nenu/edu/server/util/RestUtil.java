package com.nenu.edu.server.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Liang Jiayue
 * @Description:Rest请求工具类
 */
@Component
public class RestUtil {

    private final RestTemplate restTemplate;

    public RestUtil(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * 提交get请求
     *
     * @param url
     * @return
     */
    public JsonNode get(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        JsonNode respBody = JsonUtil.toJsonNode(responseEntity.getBody());
        return respBody;
    }
}