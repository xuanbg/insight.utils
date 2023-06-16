package com.insight.utils.redis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/6/16
 * @remark Redis Set Value Ops
 */
public class SetOps extends KeyOps{

    /**
     * 从Redis读取指定键的全部成员
     *
     * @param key 键
     * @return Value
     */
    public static List<String> members(String key) {
        var val = REDIS.opsForSet().members(key);
        return val == null ? new ArrayList<>() : new ArrayList<>(val);
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
}
