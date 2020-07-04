package com.nenu.edu.server.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nenu.edu.server.service.BaseLogService;

import java.io.IOException;

/**
 * @Author: Liang Jiayue
 * @Description:Json工具类
 */
public class JsonUtil extends BaseLogService {

    @Override
    protected Class getType() {
        return JsonUtil.class;
    }

    /**
     * 将对象转为Json字符串
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将字符串转为JsonNode
     *
     * @param jsonString
     * @return
     */
    public static JsonNode toJsonNode(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}