package com.insight.util.annotation;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luwenbao
 * @date 2018/1/3.
 * @remark
 */
public class DateDeserializer extends JsonDeserializer<Date> {
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 反序列化
     *
     * @param jp   JsonParser
     * @param ctxt DeserializationContext
     * @return Date
     */
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) {
        Date date;
        try {
            date = formatter.parse(jp.getText());
        } catch (Exception e) {
            return null;
        }
        return date;
    }
}
