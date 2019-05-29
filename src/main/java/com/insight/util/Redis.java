package com.insight.util;

import com.insight.util.common.ApplicationContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark 通用控制器基类
 */
public final class Redis {
    private static StringRedisTemplate redis = ApplicationContextHolder.getContext().getBean(StringRedisTemplate.class);

    /**
     * Redis中是否存在指定键
     *
     * @param key 键
     * @return 是否存在指定键
     */
    public static Boolean hasKey(String key) {
        return redis.hasKey(key);
    }

    /**
     * 从Redis读取指定键的值
     *
     * @param key 键
     * @return Value
     */
    public static String get(String key) {
        return redis.opsForValue().get(key);
    }

    /**
     * 从Redis读取指定键下的字段名称的值
     *
     * @param key   键
     * @param field 字段名称
     * @return Value
     */
    public static Object get(String key, String field) {
        return redis.opsForHash().get(key, field);
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, String value) {
        redis.opsForValue().set(key, value);
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @param time  时间长度
     * @param unit  时间单位
     */
    public static void set(String key, String value, long time, TimeUnit unit) {
        redis.opsForValue().set(key, value, time, unit);
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key   键
     * @param field 字段名称
     * @param value 值
     */
    public static void set(String key, String field, Object value) {
        redis.opsForHash().put(key, field, value);
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key 键
     * @param map Map 对象
     */
    public static void set(String key, Map<String, Object> map) {

        redis.opsForHash().putAll(key, map);
    }

    /**
     * 从Redis删除指定键
     *
     * @param key 键
     */
    public static void deleteKey(String key) {
        redis.delete(key);
    }

    /**
     * 删除hash
     *
     * @param key   键
     * @param field 字段名称
     */
    public static void delHashKey(String key, String field) {
        redis.opsForHash().delete(key, field);
    }

    /**
     * 获取Key过期时间
     *
     * @param key  键
     * @param unit 时间单位
     * @return 过期时间
     */
    public static long getExpire(String key, TimeUnit unit) {
        Long expire = redis.getExpire(key, unit);

        return expire == null ? 0 : expire;
    }

    /**
     * 保存如不存在
     *
     * @param key   键
     * @param value 值
     * @return 是否保存
     */
    public static boolean setIfAbsent(String key, String value) {
        Boolean absent = redis.opsForValue().setIfAbsent(key, value);

        return absent == null ? false : absent;
    }
}
