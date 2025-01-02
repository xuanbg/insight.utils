package com.insight.utils.redis;

import com.insight.utils.Json;

import java.util.concurrent.TimeUnit;

/**
 * @author 宣炳刚
 * @date 2023/6/16
 * @remark Redis String Value Ops
 */
public final class StringOps extends KeyOps {

    /**
     * 从Redis读取指定键的值
     *
     * @param key 键
     * @return Value
     */
    public static String get(String key) {
        return REDIS.opsForValue().get(key);
    }

    /**
     * 从Redis读取指定键的值
     *
     * @param key  键
     * @param type 指定的类型
     * @return Value
     */
    public static <T> T get(String key, Class<T> type) {
        var val = REDIS.opsForValue().get(key);
        if (val == null) return null;

        return Json.toBean(val, type);
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, Object value) {
        var json = Json.toJson(value);

        long expire = getExpire(key);
        if (expire < 0) {
            REDIS.opsForValue().set(key, json);
        } else if (expire > 0) {
            REDIS.opsForValue().set(key, json, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间(秒),为空、0或负数不设置过期时间
     */
    public static void set(String key, Object value, Long time) {
        set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @param time  时间长度,为空、0或负数不设置过期时间
     * @param unit  时间单位
     */
    public static void set(String key, Object value, Long time, TimeUnit unit) {
        var json = Json.toJson(value);
        REDIS.opsForValue().set(key, json, time, unit);
    }

    /**
     * 如果Key不存在,保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @return 是否保存
     */
    public static Boolean setIfAbsent(String key, Object value) {
        var json = Json.toJson(value);

        var absent = REDIS.opsForValue().setIfAbsent(key, json);
        return absent != null && absent;
    }

    /**
     * 如果Key不存在, 保存数据到Redis
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @return 是否保存
     */
    public static Boolean setIfAbsent(String key, Object value, Integer timeout) {
        return setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 如果Key不存在,保存数据到Redis
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 是否保存
     */
    public static Boolean setIfAbsent(String key, Object value, Integer timeout, TimeUnit unit) {
        var json = Json.toJson(value);

        var absent = REDIS.opsForValue().setIfAbsent(key, json, timeout, unit);
        return absent != null && absent;
    }

    /**
     * 如果Key存在, 保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @return 是否保存
     */
    public static Boolean setIfPresent(String key, Object value) {
        var json = Json.toJson(value);

        var absent = REDIS.opsForValue().setIfPresent(key, json);
        return absent != null && absent;
    }

    /**
     * 如果Key存在, 保存数据到Redis
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @return 是否保存
     */
    public static Boolean setIfPresent(String key, Object value, Integer timeout) {
        return setIfPresent(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 如果Key存在, 保存数据到Redis
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 是否保存
     */
    public static Boolean setIfPresent(String key, Object value, Integer timeout, TimeUnit unit) {
        var json = Json.toJson(value);

        var absent = REDIS.opsForValue().setIfPresent(key, json, timeout, unit);
        return absent != null && absent;
    }
}
