package com.insight.utils.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.insight.utils.DateTime;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2025/9/5
 * @remark
 */
@Configuration
public class MultiDateDeserializer extends JsonDeserializer<LocalDateTime> {

    /**
     * 反序列化时间字符串
     *
     * @param jsonParser             JsonParser
     * @param deserializationContext DeserializationContext
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        var date = jsonParser.getText();
        return DateTime.autoParseDateTime(date);
    }
}
