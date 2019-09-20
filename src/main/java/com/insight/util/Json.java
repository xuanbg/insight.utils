package com.insight.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insight.util.common.Base64Encryptor;
import com.insight.util.pojo.AccessToken;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author duxl
 * @date 2017年8月22日
 * @remark Json工具类
 */
public final class Json {

    /**
     * jackson map
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 设置JSON时间格式
     */
    private static SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        mapper.setDateFormat(myDateFormat);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private Json() {
    }

    /**
     * 克隆一个对象或将一个对象转换成新的类型并复制相同属性的值
     *
     * @param type bean类型
     * @param <T>  泛型
     * @return bean
     */
    public static <T> T clone(Object obj, Class<T> type) {
        String json = toJson(obj);

        return toBean(json, type);
    }

    /**
     * 将bean转换成json
     *
     * @param obj bean对象
     * @return json
     */
    public static String toJson(Object obj) {
        if (obj == null){
            return null;
        }

        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            LogFactory.getLog(Json.class).error("序列化对象失败!");
            return null;
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
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            log(json);
            return null;
        }
    }

    /**
     * 将json转换成指定类型的集合
     *
     * @param json           json数据
     * @param elementClasses 集合元素类型
     * @param <T>            泛型
     * @return List
     */
    public static <T> T toList(String json, Class<?>... elementClasses) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructParametricType(List.class, elementClasses));
        } catch (IOException e) {
            log(json);
            return null;
        }
    }

    /**
     * 将json字符串转换为HashMap
     *
     * @param json json
     * @return hashmap
     */
    public static Map toMap(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
            log(json);
            return null;
        }
    }

    /**
     * 将对象转换为HashMap
     *
     * @param obj 对象
     * @return hashmap
     */
    public static Map toMap(Object obj) {
        String json = toJson(obj);
        if (json == null || json.isEmpty()){
            return null;
        }

        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
            log(json);
            return null;
        }
    }

    /**
     * 先把bean转换为json字符串，再把json字符串进行base64编码
     *
     * @param obj bean
     * @return base64编码的Json
     */
    public static String toBase64(Object obj) {
        String json = toJson(obj);
        if (json == null || json.isEmpty()){
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
        String json = Base64Encryptor.decodeToString(base64Str);

        return toBean(json, type);
    }

    /**
     * 从base64码解析AccessToken
     *
     * @param base64Str accesstoken str
     * @return AccessToken
     */
    public static AccessToken toAccessToken(String base64Str) {
        return toBeanFromBase64(base64Str, AccessToken.class);
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
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getJavaType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 输出错误的JSON字符串到日志
     *
     * @param str JSON字符串
     */
    private static void log(String str) {
        LogFactory.getLog(Json.class).error("非法的JSON字符串：" + str);
    }
}
