package com.insight.util.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2018/1/4
 * @remark 令牌信息实体类
 */
public class TokenInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * Token允许的超时毫秒数(300秒)
     */
    private static final int TIME_OUT = 1000 * 300;

    /**
     * 访问令牌MD5摘要
     */
    private String hash;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 登录部门ID
     */
    private String deptId;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 令牌生命周期(秒)
     */
    private Long life;

    /**
     * 单点登录
     */
    private Boolean signInOne;

    /**
     * 租户到期时间
     */
    private Date expireDate;

    /**
     * Token过期时间
     */
    private Date expiryTime;

    /**
     * Token失效时间
     */
    private Date failureTime;

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
     * @param isReal 是否实际过期时间
     * @return Token是否过期
     */
    @JsonIgnore
    public Boolean isExpiry(Boolean isReal) {
        Date now = new Date();
        Date expiry = new Date(expiryTime.getTime() - (isReal ? TIME_OUT : 0));
        return now.after(expiry);
    }

    /**
     * Token是否失效
     *
     * @return Token是否失效
     */
    public Boolean isFailure() {
        return new Date().after(failureTime);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getLife() {
        return life;
    }

    public void setLife(Long life) {
        this.life = life;
    }

    public Boolean getSignInOne() {
        return signInOne;
    }

    public void setSignInOne(Boolean signInOne) {
        this.signInOne = signInOne;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
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
}
