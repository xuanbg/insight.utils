package com.insight.utils.redis;

import com.insight.utils.Json;

import java.util.List;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2023/6/16
 * @remark Redis Hash Value Ops
 */
public class HashOps extends KeyOps {

    /**
     * Redis中是否存在指定键和字段
     *
     * @param key   键
     * @param field 字段名称
     * @return 是否存在指定键
     */
    public static Boolean hasKey(String key, Object field) {
        return REDIS.opsForHash().hasKey(key, field);
    }

    /**
     * 读取指定键指定字段的值为字符串
     *
     * @param key   键
     * @param field 字段名称
     * @return Value
     */
    public static String get(String key, Object field) {
        var val = REDIS.opsForHash().get(key, field.toString());
        return val == null ? null : val.toString();
    }

    /**
     * 读取指定键指定字段的值为指定类型的对象
     *
     * @param key   键
     * @param field 字段名称
     * @param type  指定的类型
     * @return Value
     */
    public static <T> T get(String key, Object field, Class<T> type) {
        var val = REDIS.opsForHash().get(key, field.toString());
        if (val == null) {
            return null;
        }

        return Json.toBean(val.toString(), type);
    }

    /**
     * 读取指定键的数据为MAP
     *
     * @param key 键
     * @return Value
     */
    public static Map<Object, Object> entries(String key) {
        return REDIS.opsForHash().entries(key);
    }

    /**
     * 读取指定键的数据为指定类型的对象
     *
     * @param key  键
     * @param type 指定的类型
     * @return Value
     */
    public static <T> T entries(String key, Class<T> type) {
        var map = REDIS.opsForHash().entries(key);
        return Json.clone(map, type);
    }

    /**
     * 从Redis读取指定键下的全部字段名
     *
     * @param key 键
     * @return Keys
     */
    public static List<String> keys(String key) {
        var val = REDIS.opsForHash().keys(key);
        return Json.cloneList(val, String.class);
    }

    /**
     * 从Redis读取指定键下的全部值
     *
     * @param key 键
     * @return Value
     */
    public static List<Object> values(String key) {
        return REDIS.opsForHash().values(key);
    }

    /**
     * 从Redis读取指定键下的全部值为指定类型的对象集合
     *
     * @param key  键
     * @param type 指定的类型
     * @return Value
     */
    public static <T> List<T> values(String key, Class<T> type) {
        var val = REDIS.opsForHash().values(key);
        return Json.cloneList(val, type);
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key   键
     * @param field 字段名称
     * @param value 值
     */
    public static void put(String key, String field, Object value) {
        REDIS.opsForHash().put(key, field, value.toString());
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key 键
     * @param map Map 对象
     */
    public static void putAll(String key, Map<String, Object> map) {
        REDIS.opsForHash().putAll(key, map);
    }

    /**
     * 删除指定的Hash键
     *
     * @param key   键
     * @param field 字段名称
     */
    public static void delete(String key, String field) {
        REDIS.opsForHash().delete(key, field);
    }
}
