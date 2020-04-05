package com.insight.utils.annotation;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author luwenbao
 * @date 2018/1/3.
 * @remark
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 反序列化
     *
     * @param jp   JsonParser
     * @param ctxt DeserializationContext
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) {
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(jp.getText(), formatter);
        } catch (Exception e) {
            return null;
        }
        return date;
    }
}
