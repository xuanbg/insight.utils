package com.insight.util.service;

import com.insight.util.pojo.Reply;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark 通用控制器基类
 */
public class BaseController {

    /**
     * StringRedisTemplate
     */
    private StringRedisTemplate redis;

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

    public StringRedisTemplate getRedis() {
        return redis;
    }

    public void setRedis(StringRedisTemplate redis) {
        this.redis = redis;
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
