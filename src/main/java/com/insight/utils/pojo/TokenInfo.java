package com.insight.utils.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Json;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2018/1/4
 * @remark 令牌信息实体类
 */
public class TokenInfo implements Serializable {
    public static final int TIME_OUT = 300;
    private static final long serialVersionUID = -1L;

    /**
     * 访问令牌MD5摘要
     */
    private String hash;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 授权码生命周期(毫秒)
     */
    private Long permitLife;

    /**
     * 令牌生命周期(毫秒)
     */
    private Long life;

    /**
     * 验证来源
     */
    private Boolean verifySource;

    /**
     * 单点登录
     */
    private Boolean signInOne;

    /**
     * 是否自动刷新
     */
    private Boolean autoRefresh;

    /**
     * 租户到期时间
     */
    private LocalDate expireDate;

    /**
     * 授权码获取时间
     */
    private LocalDateTime permitTime;

    /**
     * Token过期时间
     */
    private LocalDateTime expiryTime;

    /**
     * Token失效时间
     */
    private LocalDateTime failureTime;

    /**
     * Token验证密钥
     */
    private String secretKey;

    /**
     * Token刷新密钥
     */
    private String refreshKey;

    /**
     * 功能授权代码集合
     */
    private List<String> permitFuncs;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getPermitLife() {
        return permitLife;
    }

    public void setPermitLife(Long permitLife) {
        this.permitLife = permitLife;
    }

    public Long getLife() {
        return life;
    }

    public void setLife(Long life) {
        this.life = life;
    }

    public Boolean getVerifySource() {
        return verifySource;
    }

    public void setVerifySource(Boolean verifySource) {
        this.verifySource = verifySource;
    }

    public Boolean getSignInOne() {
        return signInOne;
    }

    public void setSignInOne(Boolean signInOne) {
        this.signInOne = signInOne;
    }

    public Boolean getAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(Boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDateTime getPermitTime() {
        return permitTime;
    }

    public void setPermitTime(LocalDateTime permitTime) {
        this.permitTime = permitTime;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public LocalDateTime getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(LocalDateTime failureTime) {
        this.failureTime = failureTime;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRefreshKey() {
        return refreshKey;
    }

    public void setRefreshKey(String refreshKey) {
        this.refreshKey = refreshKey;
    }

    public List<String> getPermitFuncs() {
        return permitFuncs;
    }

    public void setPermitFuncs(List<String> permitFuncs) {
        this.permitFuncs = permitFuncs;
    }

    /**
     * 验证Token是否合法
     *
     * @param hash 令牌哈希值
     * @return Token是否合法
     */
    @JsonIgnore
    public Boolean verifyToken(String hash) {
        return this.hash.equals(hash);
    }

    /**
     * Token是否过期
     *
     * @return Token是否过期
     */
    @JsonIgnore
    public Boolean isExpiry() {
        LocalDateTime expiry = expiryTime.plusSeconds(TIME_OUT);

        return LocalDateTime.now().isAfter(expiry);
    }

    /**
     * Token是否失效
     *
     * @return Token是否失效
     */
    @JsonIgnore
    public Boolean isFailure() {
        LocalDateTime expiry = failureTime.plusSeconds(TIME_OUT);

        return LocalDateTime.now().isAfter(expiry);
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
