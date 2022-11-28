package com.insight.utils.common;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.insight.utils.DateTime;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 宣炳刚
 * @date 2019/9/21
 * @remark LocalDateTime类型序列化配置
 */
@Configuration
@ConditionalOnClass({Jackson2ObjectMapperBuilder.class, LocalDateTime.class, LocalDate.class})
public class LocalDateTimeConfig {

    /**
     * 获取序列化组件
     *
     * @param jacksonProperties JacksonProperties
     * @return SimpleModule
     */
    @Bean
    public JavaTimeModule localDateTimeModule(JacksonProperties jacksonProperties) {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTime.DEFAULT_TIME_FORMATTER));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTime.DEFAULT_TIME_FORMATTER));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_INSTANT));

        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTime.DEFAULT_DATE_FORMATTER));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTime.DEFAULT_DATE_FORMATTER));

        return module;
    }
}
