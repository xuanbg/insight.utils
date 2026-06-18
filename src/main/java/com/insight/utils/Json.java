package com.insight.utils;


import com.insight.utils.common.Base64Encryptor;
import com.insight.utils.pojo.auth.TokenKey;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.ext.javatime.deser.LocalDateDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalTimeSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author duxl
 * @date 2017年8月22日
 * @remark Json工具类
 */
public final class Json {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final JsonMapper MAPPER = new JsonMapper();

    static {
        var module = new SimpleModule();

        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));

        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));

        JsonMapper.builder()
                .addModule(module)
                .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DateTimeFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();
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

        return MAPPER.writeValueAsString(obj);
    }

    /**
     * 克隆一个对象或将一个对象转换成新的类型并复制相同属性的值
     *
     * @param type bean类型
     * @param <T>  泛型
     * @return bean
     */
    public static <T> T toBean(Object obj, Class<T> type) {
        var json = toJson(obj);
        return toBean(json, type);
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
        if (Util.isEmpty(json) || !json.trim().matches("^([\\[{])(.*?)([}\\]])$")) {
            return null;
        }

        return MAPPER.readValue(json.trim(), type);
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
        if (Util.isEmpty(json) || !json.trim().matches("^\\[(.*?)]$")) {
            return new ArrayList<>();
        }

        return MAPPER.readValue(json.trim(), getJavaType(List.class, type));
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

        //noinspection unchecked
        return MAPPER.readValue(json.trim(), Map.class);
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
     * 将json字符串转换为TreeMap
     *
     * @param json json
     * @return TreeMap
     */
    public static TreeMap toTreeMap(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        return MAPPER.readValue(json.trim(), TreeMap.class);
    }

    /**
     * 将对象转换为TreeMap
     *
     * @param obj 对象
     * @return TreeMap
     */
    public static TreeMap toTreeMap(Object obj) {
        var json = toJson(obj);
        return toTreeMap(json);
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
     * @param name 字段名称
     * @param type bean 类型
     * @return 字段值
     */
    public static <T> T getValue(String json, String name, Class<T> type) {
        return toBean(getValue(json, name), type);
    }

    /**
     * 获取json中的某个字段值
     *
     * @param json json字符串
     * @param name 字段名称
     * @param type bean 类型
     * @return 字段值
     */
    public static <T> List<T> getListValue(String json, String name, Class<T> type) {
        return toList(getValue(json, name), type);
    }

    /**
     * 获取json中的某个字段值
     *
     * @param json json字符串
     * @param name 字段名称
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
