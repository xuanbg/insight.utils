package com.insight.util;

import com.insight.util.common.ApplicationContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark 通用控制器基类
 */
public final class Redis {
    private static final StringRedisTemplate redis = ApplicationContextHolder.getContext().getBean(StringRedisTemplate.class);

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
     * 设置Key过期时间
     *
     * @param key  键
     * @param time 时间长度
     * @param unit 时间单位
     */
    public static void setExpire(String key, long time, TimeUnit unit) {
        if (!hasKey(key)) {
            return;
        }

        redis.expire(key, time, unit);
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
     * 从Redis读取指定键的值
     *
     * @param key 键
     * @return Value
     */
    public static String get(String key) {
        return redis.opsForValue().get(key);
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
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @return 是否保存
     */
    public static boolean setIfAbsent(String key, String value) {
        Boolean absent = redis.opsForValue().setIfAbsent(key, value);

        return absent == null ? false : absent;
    }

    /**
     * Redis中是否存在指定键
     *
     * @param key   键
     * @param field 字段名称
     * @return 是否存在指定键
     */
    public static Boolean hasKey(String key, String field) {
        return redis.opsForHash().hasKey(key, field);
    }

    /**
     * 从Redis读取指定键下的字段名称的值
     *
     * @param key   键
     * @param field 字段名称
     * @return Value
     */
    public static String get(String key, String field) {
        Object val = redis.opsForHash().get(key, field);

        return val == null ? null : val.toString();
    }

    /**
     * 从Redis读取指定键的值并反序列化为对象后返回
     *
     * @param key 键
     * @return Value
     */
    public static <T> T get(String key, Class<T> type) {
        Map val = redis.opsForHash().entries(key);

        return Json.clone(val, type);
    }

    /**
     * 从Redis读取指定键下的全部字段名
     *
     * @param key 键
     * @return Value
     */
    public static List<Object> getHashKeys(String key) {
        Set<Object> val = redis.opsForHash().keys(key);

        return new ArrayList<>(val);
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key   键
     * @param field 字段名称
     * @param value 值
     */
    public static void set(String key, String field, Object value) {
        redis.opsForHash().put(key, field, value.toString());
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key 键
     * @param map Map 对象
     */
    public static void set(String key, Map map) {
        Map<String, String> hashMap = new HashMap<>(32);
        for (Object k : map.keySet()) {
            Object v = map.get(k);
            hashMap.put(k.toString(), v == null ? null : v.toString());
        }

        redis.opsForHash().putAll(key, hashMap);
    }

    /**
     * 删除指定的Hash键
     *
     * @param key   键
     * @param field 字段名称
     */
    public static void deleteKey(String key, String field) {

        redis.opsForHash().delete(key, field);
    }

    /**
     * 是否指定键的成员
     *
     * @param key    键
     * @param member Set成员
     * @return 是否成员
     */
    public static Boolean isMember(String key, String member) {
        return redis.opsForSet().isMember(key, member);
    }

    /**
     * 从Redis读取指定键的全部成员
     *
     * @param key 键
     * @return Value
     */
    public static List<String> getMembers(String key) {
        Set<String> val = redis.opsForSet().members(key);

        return val == null ? null : new ArrayList<>(val);
    }

    /**
     * 以Set方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     */
    public static void add(String key, String value) {
        redis.opsForSet().add(key, value);
    }

    /**
     * 删除指定的Set成员
     *
     * @param key   键
     * @param value 值
     * @return Set成员数
     */
    public static Long remove(String key, String value) {
        return redis.opsForSet().remove(key, value);
    }
}
