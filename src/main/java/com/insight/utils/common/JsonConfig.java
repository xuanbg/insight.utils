package com.insight.utils.common;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.insight.utils.DateTime;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author 宣炳刚
 * @date 2019/9/21
 * @remark 时间类型序列化配置
 */
@Configuration
@ConditionalOnClass({Jackson2ObjectMapperBuilder.class, Long.class, LocalDateTime.class, LocalDate.class, LocalTime.class})
public class JsonConfig {

    /**
     * 获取序列化组件
     *
     * @return SimpleModule
     */
    @Bean
    public SimpleModule localDateTimeModule() {
        var simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTime.DATETIME_FORMATTER));
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTime.DATE_FORMATTER));
        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTime.TIME_FORMATTER));
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTime.DATETIME_FORMATTER));
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTime.DATE_FORMATTER));
        simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTime.TIME_FORMATTER));

        return simpleModule;
    }
}
