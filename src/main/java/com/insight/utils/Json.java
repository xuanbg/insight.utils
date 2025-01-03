package com.insight.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.insight.utils.common.Base64Encryptor;
import com.insight.utils.pojo.auth.TokenKey;
import com.insight.utils.pojo.base.BusinessException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duxl
 * @date 2017年8月22日
 * @remark Json工具类
 */
public final class Json {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        var module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));
        module.addSerializer(Date.class, new DateSerializer());

        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));
        module.addDeserializer(Date.class, new DateDeserializers.DateDeserializer());

        MAPPER.registerModule(module);
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 克隆一个对象或将一个对象转换成新的类型并复制相同属性的值
     *
     * @param type bean类型
     * @param <T>  泛型
     * @return bean
     */
    public static <T> T clone(Object obj, Class<T> type) {
        var json = toJson(obj);
        return toBean(json, type);
    }

    /**
     * 将json转换成指定类型的集合
     *
     * @param <T>         泛型
     * @param obj         bean类型
     * @param elementType 集合元素类型
     * @return List
     */
    public static <T> T cloneList(Object obj, Class<?>... elementType) {
        var json = toJson(obj);
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructParametricType(List.class, elementType));
        } catch (IOException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    /**
     * 将bean转换成json
     *
     * @param obj bean对象
     * @return json
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        }

        try {
            return MAPPER.writeValueAsString(obj);
        } catch (IOException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    /**
     * 把json字符串转换为相应的JavaBean对象
     *
     * @param json json数据
     * @param type bean 类型
     * @param <T>  泛型
     * @return bean
     */
    public static <T> T toBean(String json, Class<T> type) {
        if (Util.isNotEmpty(json) && json.trim().matches("^\\s*([{\\[])(.*?)([}\\]])\\s*$")) {
            try {
                return MAPPER.readValue(json.trim(), type);
            } catch (IOException ex) {
                throw new BusinessException(ex.getMessage());
            }
        } else {
            return null;
        }
    }

    /**
     * 将json转换成指定类型的集合
     *
     * @param obj  对象
     * @param type 集合元素类型
     * @param <T>  泛型
     * @return List
     */
    public static <T> List<T> toList(Object obj, Class<T> type) {
        var json = toJson(obj);
        return toList(json, type);
    }

    /**
     * 将json转换成指定类型的集合
     *
     * @param json json数据
     * @param type 集合元素类型
     * @param <T>  泛型
     * @return List
     */
    public static <T> List<T> toList(String json, Class<T> type) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return MAPPER.readValue(json, new TypeReference<List<T>>() {});
        } catch (IOException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    /**
     * 将json字符串转换为HashMap
     *
     * @param json json
     * @return hashmap
     */
    public static Map<String, Object> toMap(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            //noinspection unchecked
            return MAPPER.readValue(json, HashMap.class);
        } catch (IOException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    /**
     * 将对象转换为HashMap
     *
     * @param obj 对象
     * @return hashmap
     */
    public static Map<String, Object> toMap(Object obj) {
        var json = toJson(obj);
        return toMap(json);
    }

    /**
     * 将对象转换为值为String类型的HashMap
     *
     * @param obj 对象
     * @return hashmap
     */
    public static Map<String, String> toStringValueMap(Object obj) {
        var json = toJson(obj);
        return toStringValueMap(json);
    }

    /**
     * 将Json字符串转换为值为String类型的HashMap
     *
     * @param json json字符串
     * @return hashmap
     */
    public static Map<String, String> toStringValueMap(String json) {
        var obj = toMap(json);
        if (obj == null) {
            return null;
        }

        Map<String, String> map = new HashMap<>(16);
        for (var set : obj.entrySet()) {
            var value = set.getValue();
            map.put(set.getKey(), toJson(value));
        }

        return map;
    }

    /**
     * 先把bean转换为json字符串，再把json字符串进行base64编码
     *
     * @param obj bean
     * @return base64编码的Json
     */
    public static String toBase64(Object obj) {
        var json = toJson(obj);
        if (json == null || json.isEmpty()) {
            return null;
        }

        return Base64Encryptor.encode(json);
    }

    /**
     * 把base64编码过的json字符串转换为相应的JavaBean对象
     * 先转换为json字符串，再转换为bean
     *
     * @param base64Str base64编码过的json字符串
     * @param type      bean类型
     * @param <T>       泛型
     * @return bean
     */
    public static <T> T toBeanFromBase64(String base64Str, Class<T> type) {
        var json = Base64Encryptor.decodeToString(base64Str);
        return toBean(json, type);
    }

    /**
     * 从base64码解析AccessToken
     *
     * @param base64Str accesstoken str
     * @return AccessToken
     */
    public static TokenKey toToken(String base64Str) {
        return toBeanFromBase64(base64Str, TokenKey.class);
    }

    /**
     * 获取json中的某个字段值
     *
     * @param json json字符串
     * @return 字段值
     */
    public static Object getValue(String json, String name) {
        return toMap(json).get(name);
    }

    /**
     * 获取泛型Collection JavaType
     *
     * @param collectionClass 泛型的Collection
     * @param elementType     元素类
     * @return JavaType Java类型
     */
    public static JavaType getJavaType(Class<?> collectionClass, Class<?>... elementType) {
        return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementType);
    }
}
