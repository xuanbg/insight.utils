package com.insight.utils.common;

import com.insight.utils.DateTime;
import org.springframework.context.annotation.Configuration;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2025/9/5
 * @remark
 */
@Configuration
public class MultiDateDeserializer extends ValueDeserializer<LocalDateTime> {

    /**
     * 反序列化时间字符串
     *
     * @param jsonParser             JsonParser
     * @param deserializationContext DeserializationContext
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        var date = jsonParser.getString();
        return DateTime.autoParseDateTime(date);
    }
}
