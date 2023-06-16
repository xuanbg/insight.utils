package com.insight.utils.redis;

import com.insight.utils.common.ContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author 宣炳刚
 * @date 2023/6/16
 * @remark Redis Key Ops
 */
public class KeyOps {
    protected static final StringRedisTemplate REDIS = ContextHolder.getContext().getBean(StringRedisTemplate.class);

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
    public static void delete(String key) {
        REDIS.delete(key);
    }

    /**
     * 获取Key过期时间
     *
     * @param key 键
     * @return 过期时间
     */
    public static Long getExpire(String key) {
        return REDIS.getExpire(key);
    }

    /**
     * 设置Key过期时间
     *
     * @param key  键
     * @param time 时间长度
     * @param unit 时间单位
     */
    public static void setExpire(String key, long time, TimeUnit unit) {
        if (time == 0) {
            delete(key);
        } else {
            REDIS.expire(key, time, unit);
        }
    }

    /**
     * 修改过期时间
     *
     * @param key  键
     * @param time 要改变的秒数
     */
    public static void changeExpire(String key, long time) {
        var expire = getExpire(key);
        if (expire == null){
            return;
        }

        if (expire + time == 0) {
            delete(key);
        } else {
            setExpire(key, expire + time, TimeUnit.SECONDS);
        }
    }
}
