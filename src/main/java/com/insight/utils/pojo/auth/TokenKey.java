package com.insight.utils.pojo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

/**
 * @author xuan
 * @date 2017年9月06日
 * @remark 访问令牌，客户端验证身份所需的凭证
 */
public class TokenKey extends BaseXo {

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 安全码，用于验证令牌合法性
     */
    protected String secret;

    public TokenKey() {
    }

    public TokenKey(Long appId, Long tenantId, Long userId) {
        this.appId = appId;
        this.tenantId = tenantId;
        this.userId = userId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonIgnore
    public String getKey() {
        return "Token:%d:%d:%d".formatted(appId, tenantId == null ? 0 : tenantId, userId);
    }
}