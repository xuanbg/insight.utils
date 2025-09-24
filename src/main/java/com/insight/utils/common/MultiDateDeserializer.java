package com.insight.utils.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.insight.utils.pojo.base.BusinessException;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2025/9/5
 * @remark
 */
@Configuration
public class MultiDateDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final List<DateTimeFormatter> SUPPORTED_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_DATE_TIME
    );

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
        for (var formatter : SUPPORTED_FORMATTERS) {
            try {
                return isDateOnlyFormat(formatter)
                        ? LocalDateTime.parse(date + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        : LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException ignored) {
            }

        }
        throw new BusinessException("无法解析的时间格式: " + date);
    }

    /**
     * 判断时间格式是否为仅包含日期部分
     *
     * @param formatter 时间格式
     * @return 布尔值
     */
    private boolean isDateOnlyFormat(DateTimeFormatter formatter) {
        String pattern = formatter.toString();
        return pattern.contains("yyyy") &&
               !pattern.contains("HH") &&
               !pattern.contains("mm") &&
               !pattern.contains("ss");
    }
}
