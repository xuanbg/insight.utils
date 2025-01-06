package com.insight.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author duxl
 * @date 2017/11/17
 * @remark redis分布式锁
 */
@Component
public class LockHandler {
    private static final Logger logger = LoggerFactory.getLogger(LockHandler.class);

    /**
     * 单个业务持有锁的时间30s,防止死锁
     */
    private final static long LOCK_EXPIRE = 30 * 1000L;
    /**
     * 默认100ms尝试一次
     */
    private final static long LOCK_TRY_INTERVAL = 300L;
    /**
     * 默认尝试3s
     */
    private final static long LOCK_TRY_TIMEOUT = 3 * 1000L;

    /**
     * 操作redis获取全局锁
     *
     * @param param           锁的名称
     * @param timeout         超时时间(秒)
     * @param tryInterval     多少ms尝试一次
     * @param paramExpireTime 过期时间(秒)
     * @return true 获取成功，false获取失败
     */
    public boolean getLock(LockParam param, long timeout, long tryInterval, long paramExpireTime) {
        String key = "Lock:" + param.getKey();
        String value = param.getValue();
        try {
            if (value == null || value.isEmpty()) {
                return false;
            }

            long startTime = System.currentTimeMillis();
            while (true) {
                if (StringOps.setIfAbsent(key, param.getValue())) {
                    StringOps.set(key, param.getValue(), paramExpireTime);
                    return true;
                }

                if (System.currentTimeMillis() - startTime > timeout * 1000) {
                    return false;
                }

                Thread.sleep(tryInterval);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * 尝试获取全局锁
     *
     * @param param 锁的名称
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(LockParam param) {
        return getLock(param, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param param   锁的名称
     * @param timeout 获取超时时间 单位ms
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(LockParam param, long timeout) {
        return getLock(param, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param param       锁的名称
     * @param timeout     获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(LockParam param, long timeout, long tryInterval) {
        return getLock(param, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param param           锁的名称
     * @param timeout         获取锁的超时时间
     * @param tryInterval     多少毫秒尝试获取一次
     * @param paramExpireTime 锁的过期
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(LockParam param, long timeout, long tryInterval, long paramExpireTime) {
        return getLock(param, timeout, tryInterval, paramExpireTime);
    }

    /**
     * 释放锁
     */
    public void releaseLock(LockParam param) {
        String key = "Lock:" + param.getKey();
        String val = StringOps.get(key);
        if (val.equals(param.getValue())) {
            KeyOps.delete(key);
        }
    }
}
