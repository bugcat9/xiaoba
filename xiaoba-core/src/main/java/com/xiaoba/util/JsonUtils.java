package com.xiaoba.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zhouning
 */
@Slf4j
public class JsonUtils {

    private final static ObjectMapper OBJ_MAPPER = new ObjectMapper();

    /**
     * Json字符串转化成对象
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T toObj(String jsonString, Class<T> clazz) {
        OBJ_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return OBJ_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error("Json字符串转化成对象出错",e);
        }
        return null;
    }

    /**
     * javaBean 转化成json字符串
     * @param obj
     * @return
     * @throws Exception
     */
    public static String toJson(Object obj) {
        if(obj instanceof Integer || obj instanceof Long || obj instanceof Float ||
                obj instanceof Double || obj instanceof Boolean || obj instanceof String){
            return String.valueOf(obj);
        }
        try {
            return OBJ_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("转化成json字符串",e);
        }
        return null;
    }
}
