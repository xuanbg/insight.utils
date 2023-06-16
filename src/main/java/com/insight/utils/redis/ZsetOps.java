package com.insight.utils.redis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/6/16
 * @remark Redis ZSet Value Ops
 */
public class ZsetOps extends KeyOps {

    /**
     * 按顺序获取Zset的全部数据
     *
     * @param key 键
     * @return SET
     */
    public static List<String> range(String key) {
        return range(key, -1L);
    }

    /**
     * 按顺序获取Zset的指定数量的数据
     *
     * @param key   键
     * @param count 对象数量
     * @return SET
     */
    public static List<String> range(String key, Long count) {
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
    public static List<String> range(String key, Long start, Long end) {
        var val = REDIS.opsForZSet().range(key, start, end);
        return val == null || val.isEmpty() ? new ArrayList<>() : new ArrayList<>(val);
    }

    /**
     * 按倒序获取Zset的全部数据
     *
     * @param key 键
     * @return SET
     */
    public static List<String> reverseRange(String key) {
        return reverseRange(key, -1L);
    }

    /**
     * 按倒序获取Zset指定数量的数据
     *
     * @param key   键
     * @param count 对象数量
     * @return SET
     */
    public static List<String> reverseRange(String key, Long count) {
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
    public static List<String> reverseRange(String key, Long start, Long end) {
        var val = REDIS.opsForZSet().reverseRange(key, start, end);
        return val == null || val.isEmpty() ? new ArrayList<>() : new ArrayList<>(val);
    }

    /**
     * 以Set方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     */
    public static void add(String key, String value) {
        REDIS.opsForZSet().add(key, value, 0.001);
    }

    /**
     * 对指定值的Score进行递增
     *
     * @param key   键
     * @param value 值
     */
    public static void incrementScore(String key, String value) {
        REDIS.opsForZSet().incrementScore(key, value, 0.001);
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
    public static Long remove(String key, String value) {
        return REDIS.opsForZSet().remove(key, value);
    }
}
