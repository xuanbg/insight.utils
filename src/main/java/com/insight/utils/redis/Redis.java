package com.insight.utils.redis;

import com.insight.utils.common.ContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark Redis工具类
 */
public final class Redis {
    private static final StringRedisTemplate REDIS = ContextHolder.getContext().getBean(StringRedisTemplate.class);

    /**
     * Redis中是否存在指定键
     *
     * @param key 键
     * @return 是否存在指定键
     */
    public static Boolean hasKey(String key) {
        return REDIS.hasKey(key);
    }

    /**
     * 从Redis删除指定键
     *
     * @param key 键
     */
    public static void deleteKey(String key) {
        REDIS.delete(key);
    }

    /**
     * 获取Key过期时间
     *
     * @param key 键
     * @return 过期时间
     */
    public static long getExpire(String key) {
        var expire = REDIS.getExpire(key);
        return expire == null ? -1 : expire;
    }

    /**
     * 修改过期时间
     *
     * @param key  键
     * @param time 要改变的秒数
     */
    public static void changeExpire(String key, long time) {
        var expire = getExpire(key);
        if (expire + time <= 0) {
            return;
        }

        setExpire(key, expire + time, TimeUnit.SECONDS);
    }

    /**
     * 设置Key过期时间
     *
     * @param key  键
     * @param time 时间长度
     * @param unit 时间单位
     */
    public static void setExpire(String key, long time, TimeUnit unit) {
        REDIS.expire(key, time, unit);
    }

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
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, String value) {
        long expire = getExpire(key);
        if (expire > 0) {
            REDIS.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
        } else if (expire < 0) {
            REDIS.opsForValue().set(key, value);
        }
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间(秒),为空、0或负数不设置过期时间
     */
    public static void set(String key, String value, Long time) {
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
    public static void set(String key, String value, Long time, TimeUnit unit) {
        REDIS.opsForValue().set(key, value, time, unit);
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @return 是否保存
     */
    public static boolean setIfAbsent(String key, String value) {
        Boolean absent = REDIS.opsForValue().setIfAbsent(key, value);
        return absent != null && absent;
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 是否保存
     */
    public static boolean setIfAbsent(String key, String value, Integer timeout, TimeUnit unit) {
        Boolean absent = REDIS.opsForValue().setIfAbsent(key, value, timeout, unit);
        return absent != null && absent;
    }

    /**
     * Redis中是否存在指定键
     *
     * @param key   键
     * @param field 字段名称
     * @return 是否存在指定键
     */
    public static Boolean hasKey(String key, String field) {
        return REDIS.opsForHash().hasKey(key, field);
    }

    /**
     * 从Redis读取指定键下的字段名称的值
     *
     * @param key   键
     * @param field 字段名称
     * @return Value
     */
    public static String get(String key, String field) {
        Object val = REDIS.opsForHash().get(key, field);
        return val == null ? null : val.toString();
    }

    /**
     * 从Redis读取指定键下的字段名称的值
     *
     * @param key 键
     * @return Value
     */
    public static Map<Object, Object> getEntity(String key) {
        return REDIS.opsForHash().entries(key);
    }

    /**
     * 从Redis读取指定键下的全部字段名
     *
     * @param key 键
     * @return Keys
     */
    public static List<Object> getHashKeys(String key) {
        Set<Object> val = REDIS.opsForHash().keys(key);
        return new ArrayList<>(val);
    }

    /**
     * 从Redis读取指定键下的全部值名
     *
     * @param key 键
     * @return Value
     */
    public static List<Object> getHash(String key) {
        return REDIS.opsForHash().values(key);
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key   键
     * @param field 字段名称
     * @param value 值
     */
    public static void setHash(String key, String field, Object value) {
        REDIS.opsForHash().put(key, field, value.toString());
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key 键
     * @param map Map 对象
     */
    public static void setHash(String key, Map<String, String> map) {
        REDIS.opsForHash().putAll(key, map);
    }

    /**
     * 删除指定的Hash键
     *
     * @param key   键
     * @param field 字段名称
     */
    public static void deleteKey(String key, String field) {
        REDIS.opsForHash().delete(key, field);
    }

    /**
     * 是否指定键的成员
     *
     * @param key    键
     * @param member Set成员
     * @return 是否成员
     */
    public static Boolean isMember(String key, String member) {
        return REDIS.opsForSet().isMember(key, member);
    }

    /**
     * 从Redis读取指定键的全部成员
     *
     * @param key 键
     * @return Value
     */
    public static List<String> getMembers(String key) {
        Set<String> val = REDIS.opsForSet().members(key);
        return val == null ? null : new ArrayList<>(val);
    }

    /**
     * 以Set方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     */
    public static void add(String key, String value) {
        REDIS.opsForSet().add(key, value);
    }

    /**
     * 删除指定的Set成员
     *
     * @param key   键
     * @param value 值
     * @return Set成员数
     */
    public static Long remove(String key, String value) {
        return REDIS.opsForSet().remove(key, value);
    }

    /**
     * 按顺序获取Zset的全部数据
     *
     * @param key 键
     * @return SET
     */
    public static Set<String> range(String key) {
        return range(key, -1L);
    }

    /**
     * 按顺序获取Zset的指定数量的数据
     *
     * @param key   键
     * @param count 对象数量
     * @return SET
     */
    public static Set<String> range(String key, Long count) {
        return range(key, 0L, count);
    }

    /**
     * 按顺序获取指定范围的Zset数据
     *
     * @param key   键
     * @param start 开始位置
     * @param end   截止位置
     * @return SET
     */
    public static Set<String> range(String key, Long start, Long end) {
        var size = REDIS.opsForZSet().size(key);
        if (size == null || size == 0) {
            return new HashSet<>();
        }

        return REDIS.opsForZSet().range(key, start, end);
    }

    /**
     * 按倒序获取Zset的全部数据
     *
     * @param key 键
     * @return SET
     */
    public static Set<String> reverseRange(String key) {
        return reverseRange(key, -1L);
    }

    /**
     * 按倒序获取Zset指定数量的数据
     *
     * @param key   键
     * @param count 对象数量
     * @return SET
     */
    public static Set<String> reverseRange(String key, Long count) {
        return reverseRange(key, 0L, count);
    }

    /**
     * 获取指定范围的Zset数据
     *
     * @param key   键
     * @param start 开始位置
     * @param end   截止位置
     * @return SET
     */
    public static Set<String> reverseRange(String key, Long start, Long end) {
        var size = REDIS.opsForZSet().size(key);
        if (size == null || size == 0) {
            return new HashSet<>();
        }

        return REDIS.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 以Set方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     */
    public static void addZset(String key, String value) {
        REDIS.opsForZSet().add(key, value, 1);
    }

    /**
     * 对指定值的Score进行递增
     *
     * @param key   键
     * @param value 值
     */
    public static void incrementScore(String key, String value) {
        REDIS.opsForZSet().incrementScore(key, value, 1);
    }

    /**
     * 获取指定值的Score
     *
     * @param key   键
     * @param value 值
     * @return Score
     */
    public static Double score(String key, String value) {
        return REDIS.opsForZSet().score(key, value);
    }

    /**
     * 获取指定值的排名
     *
     * @param key   键
     * @param value 值
     * @return 排名
     */
    public static Long rank(String key, String value) {
        return REDIS.opsForZSet().rank(key, value);
    }

    /**
     * 删除指定的Set成员
     *
     * @param key   键
     * @param value 值
     * @return Set成员数
     */
    public static Long removeZset(String key, String value) {
        return REDIS.opsForZSet().remove(key, value);
    }
}
