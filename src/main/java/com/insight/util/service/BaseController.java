package com.insight.util.service;

import com.insight.util.DateHelper;
import com.insight.util.Redis;
import com.insight.util.pojo.Reply;
import com.insight.util.pojo.TokenInfo;
import com.insight.util.pojo.LoginInfo;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark 通用控制器基类
 */
public class BaseController {

    /**
     * 令牌ID
     */
    protected String tokenId;

    /**
     * 验证结果
     */
    protected Reply reply;

    /**
     * 令牌持有人信息
     */
    protected LoginInfo loginInfo = new LoginInfo();

    /**
     * 会话合法性验证
     *
     * @param token       令牌
     * @param fingerprint 用户特征串
     * @return Reply
     */
    protected boolean verify(String token, String fingerprint) {
        return verify(token, fingerprint, null, null);
    }

    /**
     * 会话合法性验证
     *
     * @param token       令牌
     * @param fingerprint 用户特征串
     * @param key         操作权限代码
     * @return Reply
     */
    protected boolean verify(String token, String fingerprint, String key) {
        return verify(token, fingerprint, key, null);
    }

    /**
     * 会话合法性验证
     *
     * @param token       令牌
     * @param fingerprint 用户特征串
     * @param key         操作权限代码
     * @param userId      用户ID
     * @return Reply
     */
    protected boolean verify(String token, String fingerprint, String key, String userId) {
        Verify verify = new Verify(token, fingerprint);
        if (verify.userIsEquals(userId)) {
            key = null;
        }

        tokenId = verify.getTokenId();
        reply = verify.compare(key);
        if (!reply.getSuccess()) {
            return false;
        }

        TokenInfo basis = verify.getBasis();
        loginInfo.setAppId(basis.getAppId());
        loginInfo.setTenantId(basis.getTenantId());
        loginInfo.setDeptId(basis.getDeptId());
        loginInfo.setUserId(verify.getUserId());
        loginInfo.setUserName(verify.getUserName());

        return true;
    }

    /**
     * 获取限流计时周期剩余秒数
     *
     * @param key     键值
     * @param seconds 限流计时周期秒数
     * @return 剩余秒数
     */
    protected Integer getSurplus(String key, Integer seconds) {
        if (key == null || key.isEmpty() || seconds == null || seconds.equals(0)) {
            return 0;
        }

        key = "Surplus:" + key;
        String val = Redis.get(key);
        if (val == null || val.isEmpty()) {
            Redis.set(key, DateHelper.getDateTime(), seconds, TimeUnit.SECONDS);

            return 0;
        }

        Date time = DateHelper.parseDateTime(val);
        long bypast = System.currentTimeMillis() - Objects.requireNonNull(time).getTime();
        if (bypast > 1000) {
            return seconds - (int) bypast / 1000;
        }

        // 调用时间间隔低于1秒时,重置调用时间为当前时间作为惩罚
        Redis.set(key, DateHelper.getDateTime(), seconds, TimeUnit.SECONDS);

        return seconds;
    }

    /**
     * 是否被限流(超过限流计时周期最大访问次数)
     *
     * @param key     键值
     * @param seconds 限流计时周期秒数
     * @param max     调用限制次数
     * @return 是否限制访问
     */
    protected Boolean isLimited(String key, Integer seconds, Integer max) {
        if (key == null || key.isEmpty() || seconds == null || seconds.equals(0)) {
            return false;
        }

        // 如记录不存在,则记录访问次数为1
        key = "Limit:" + key;
        String val = Redis.get(key);
        if (val == null || val.isEmpty()) {
            Redis.set(key, "1", seconds, TimeUnit.SECONDS);

            return false;
        }

        // 读取访问次数,如次数超过限制,返回true,否则访问次数增加1次
        Integer count = Integer.valueOf(val);
        long expire = Redis.getExpire(key, TimeUnit.SECONDS);
        if (count > max) {
            return true;
        }

        count++;
        Redis.set(key, count.toString(), expire, TimeUnit.SECONDS);
        return false;
    }

    /**
     * 是否被限流(超过限流计时周期最大访问次数)
     *
     * @param key     键值
     * @param seconds 限流计时周期秒数
     * @param total   限流计时大周期秒数
     * @param max     调用限制次数
     * @return 是否限制访问
     */
    protected Boolean isLimited(String key, Integer seconds, Integer total, Integer max) {
        Integer surplus = getSurplus(key, seconds);
        if (surplus > 0) {
            return true;
        }

        return isLimited(key, total, max);
    }
}
