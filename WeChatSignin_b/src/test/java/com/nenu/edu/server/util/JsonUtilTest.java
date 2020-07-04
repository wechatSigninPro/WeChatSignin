package com.nenu.edu.server.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nenu.edu.server.BaseTest;
import com.nenu.edu.server.enumeration.ResponseCode;
import com.nenu.edu.server.po.User;
import com.nenu.edu.server.web.response.ResponseWrapper;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:31 2020/7/4
 */
public class JsonUtilTest extends BaseTest {

    @Override
    protected Class getTargetClass() {
        return JsonUtilTest.class;
    }

    @Test
    public void toJsonString() {
        User user = new User();
        user.setId(1L);
        user.setName("xiaofeig");
        log.info(JsonUtil.toJsonString(user));

        ResponseWrapper responseWrapper = new ResponseWrapper(ResponseCode.AUTH_FAILED_WITHOUT_TOKEN);
        log.info(JsonUtil.toJsonString(responseWrapper));
    }

    @Test
    public void testToString() throws IOException {
        String jsonStr = "{\"name\":\"xiaofeig\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        log.info(jsonNode.get("name").asText());
    }

}