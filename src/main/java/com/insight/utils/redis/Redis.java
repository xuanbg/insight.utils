package com.insight.utils.redis;

import com.insight.utils.common.ContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark Redis工具类
 */
public final class Redis {
    private static final StringRedisTemplate REDIS = ContextHolder.getContext().getBean(StringRedisTemplate.class);

    /**
     * 获取Key过期时间
     *
     * @param key 键
     * @return 过期时间
     */
    private static long getExpire(String key) {
        Long expire = REDIS.getExpire(key);

        return expire == null ? 0 : expire;
    }

    /**
     * 设置Key过期时间
     *
     * @param key  键
     * @param time 时间长度
     * @param unit 时间单位
     */
    private static void setExpire(String key, long time, TimeUnit unit) {
        REDIS.expire(key, time, unit);
    }

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
     * 修改过期时间
     *
     * @param key  键
     * @param time 要改变的秒数
     */
    public static void changeExpire(String key, long time) {
        Long expire = REDIS.getExpire(key);
        if (expire == null || expire + time <= 0) {
            return;
        }

        setExpire(key, expire + time, TimeUnit.SECONDS);
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
        REDIS.opsForValue().set(key, value);
        if (expire > 0) {
            setExpire(key, expire, TimeUnit.SECONDS);
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
        REDIS.opsForValue().set(key, value);
        if (time != null && time > 0) {
            setExpire(key, time, unit);
        }
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
     * @return Value
     */
    public static List<Object> getHashKeys(String key) {
        Set<Object> val = REDIS.opsForHash().keys(key);

        return new ArrayList<>(val);
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
     * @param key  键
     * @param map  Map 对象
     * @param time 过期时间(秒),为空、0或负数不设置过期时间
     */
    public static void setHash(String key, Map<String, String> map, Long time) {
        REDIS.opsForHash().putAll(key, map);
        if (time != null && time > 0) {
            setExpire(key, time, TimeUnit.SECONDS);
        }
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
     * @param time  过期时间(秒),为空、0或负数不设置过期时间
     */
    public static void add(String key, String value, Long time) {
        REDIS.opsForSet().add(key, value);
        if (time != null && time > 0) {
            setExpire(key, time, TimeUnit.SECONDS);
        }
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
}
