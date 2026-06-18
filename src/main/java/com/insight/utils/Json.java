package com.insight.utils;


import com.insight.utils.common.Base64Encryptor;
import com.insight.utils.pojo.auth.TokenKey;
import com.insight.utils.pojo.base.BusinessException;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.*;

/**
 * @author duxl
 * @date 2017年8月22日
 * @remark Json工具类
 */
public final class Json {
    private static final String objReg = "^\\s*\\{\\s*(\"[^\"]*\"\\s*:\\s*(\"[^\"]*\"|true|false|null|-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?)\\s*(,\\s*\"[^\"]*\"\\s*:\\s*(\"[^\"]*\"|true|false|null|-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?)\\s*)*)?\\s*}\\s*$";
    private static final String listReg = "^\\s*\\[\\s*((\"[^\"]*\"|true|false|null|-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?)\\s*(,\\s*(\"[^\"]*\"|true|false|null|-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?)\\s*)*)?\\s*]\\s*$";
    private static final JsonMapper MAPPER = JsonMapper.builder().build();

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
        } catch (JacksonException e) {
            throw new BusinessException("对象序列化失败！失败原因是：" + e.getMessage());
        }
    }

    /**
     * 将 Java 对象序列化为格式化的 JSON 字符串（便于阅读）
     *
     * @param obj 待序列化的对象
     * @return 格式化后的 JSON 字符串
     */
    public static String toPrettyJson(Object obj) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);
        } catch (JacksonException e) {
            throw new BusinessException("对象序列化失败！失败原因是：" + e.getMessage());
        }
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
        if (json == null || !json.trim().matches("^\\s*\\{.*}\\s*$")) {
            return null;
        }

        try {
            return MAPPER.readValue(json, type);
        } catch (JacksonException e) {
            throw new BusinessException("对象序列化失败！失败原因是：" + e.getMessage() + "  JSON字符串是：" + json);
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
        if (json == null || !json.trim().matches("^\\s*\\[.*]\\s*$")) {
            return new ArrayList<>();
        }

        try {
            var javaType = MAPPER.getTypeFactory().constructParametricType(List.class, type);
            return MAPPER.readValue(json, javaType);
        } catch (JacksonException e) {
            throw new BusinessException("对象序列化失败！失败原因是：" + e.getMessage() + "  JSON字符串是：" + json);
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
     * 将json字符串转换为HashMap
     *
     * @param json json
     * @return hashmap
     */
    public static Map<String, Object> toMap(String json) {
        if (json == null || !json.trim().matches("^\\s*\\{.*}\\s*$")) {
            return null;
        }

        try {
            return MAPPER.readValue(json, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (JacksonException e) {
            throw new BusinessException("对象序列化失败！失败原因是：" + e.getMessage() + "  JSON字符串是：" + json);
        }
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
     * 将json字符串转换为TreeMap
     *
     * @param json json
     * @return TreeMap
     */
    public static TreeMap toTreeMap(String json) {
        if (json == null || !json.trim().matches("^\\s*\\{.*}\\s*$")) {
            return null;
        }

        try {
            return MAPPER.readValue(json, new TypeReference<TreeMap<String, Object>>() {
            });
        } catch (JacksonException e) {
            throw new BusinessException("对象序列化失败！失败原因是：" + e.getMessage() + "  JSON字符串是：" + json);
        }
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
        if (json == null || !json.trim().matches("^\\s*\\{.*}\\s*$")) {
            return null;
        }

        try {
            return MAPPER.readValue(json, new TypeReference<TreeMap<String, String>>() {
            });
        } catch (JacksonException e) {
            throw new BusinessException("对象序列化失败！失败原因是：" + e.getMessage() + "  JSON字符串是：" + json);
        }
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
}
