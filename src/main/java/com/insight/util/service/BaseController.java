package com.insight.util.service;

import com.insight.util.DateHelper;
import com.insight.util.Redis;
import com.insight.util.pojo.Reply;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
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
     * HttpServletRequest
     */
    private HttpServletRequest request;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 用户媒介
     */
    private String userAgent;

    /**
     * 验证结果
     */
    private Reply reply;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 登录部门ID
     */
    private String deptId;

    /**
     * 登录部门编码
     */
    private String deptCode;

    /**
     * 登录部门名称
     */
    private String deptName;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 会话合法性验证
     *
     * @return Reply
     */
    public Reply verify() {
        return verify(null, null);
    }

    /**
     * 会话合法性验证
     *
     * @param key 操作权限代码
     * @return Reply
     */
    public Reply verify(String key) {
        return verify(key, null);
    }

    /**
     * 会话合法性验证
     *
     * @param key    操作权限代码
     * @param userId 用户ID
     * @return Reply
     */
    public Reply verify(String key, String userId) {
        Verify verify = new Verify(accessToken, userAgent);
        if (verify.userIsEquals(userId)){
            key = null;
        }

        return verify.compare(key);
    }

    /**
     * 获取限流计时周期剩余秒数
     *
     * @param key     键值
     * @param seconds 限流计时周期秒数
     * @return 剩余秒数
     */
    public Integer getSurplus(String key, Integer seconds) {
        if (key == null || key.isEmpty() || seconds == null || seconds.equals(0)) {
            return 0;
        }

        key = "Limit:" + key;
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
    public Boolean isLimited(String key, Integer seconds, Integer max) {
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
        Long expire = Redis.getExpire(key, TimeUnit.SECONDS);
        if (count > max) {
            return true;
        }

        count++;
        Redis.set(key, count.toString(), expire, TimeUnit.SECONDS);
        return false;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Reply getReply() {
        return reply;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
