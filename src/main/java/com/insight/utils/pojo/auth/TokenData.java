package com.insight.utils.pojo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.DateTime;
import com.insight.utils.pojo.base.BaseXo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2018/1/4
 * @remark 令牌信息实体类
 */
public class TokenData extends BaseXo {
    public static final int TIME_OUT = 300;

    /**
     * 访问令牌MD5摘要
     */
    private String hash;

    /**
     * 授权码生命周期(秒)
     */
    private Long permitLife;

    /**
     * 令牌生命周期(秒)
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

    /**
     * 用户登录信息
     */
    protected LoginInfo loginInfo;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getPermitLife() {
        return permitLife == null ? 0L : permitLife;
    }

    public void setPermitLife(Long permitLife) {
        this.permitLife = permitLife;
    }

    public Long getLife() {
        return life == null ? 0L : life;
    }

    public void setLife(Long life) {
        this.life = life;
    }

    public Boolean getVerifySource() {
        return verifySource != null && verifySource;
    }

    public void setVerifySource(Boolean verifySource) {
        this.verifySource = verifySource;
    }

    public Boolean getSignInOne() {
        return signInOne != null && signInOne;
    }

    public void setSignInOne(Boolean signInOne) {
        this.signInOne = signInOne;
    }

    public Boolean getAutoRefresh() {
        return getLife() > 0 && autoRefresh != null && autoRefresh;
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
        return permitFuncs == null ? new ArrayList<>() : permitFuncs;
    }

    public void setPermitFuncs(List<String> permitFuncs) {
        this.permitFuncs = permitFuncs;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    /**
     * 获取应用ID
     *
     * @return 应用ID
     */
    @JsonIgnore
    public Long getAppId() {
        return loginInfo.getAppId();
    }

    /**
     * 设置应用ID
     *
     * @param id 应用ID
     */
    @JsonIgnore
    public void setAppId(Long id) {
        loginInfo.setAppId(id);
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    @JsonIgnore
    public Long getUserId() {
        return loginInfo.getId();
    }

    /**
     * 获取租户ID
     *
     * @return 租户ID
     */
    @JsonIgnore
    public Long getTenantId() {
        return loginInfo.getTenantId();
    }

    /**
     * 设置登租户名称
     *
     * @param name 租户名称
     */
    @JsonIgnore
    public void setTenantName(String name) {
        loginInfo.setTenantName(name);
    }

    /**
     * 设置登录机构ID
     *
     * @param id 机构ID
     */
    @JsonIgnore
    public void setOrgId(Long id) {
        loginInfo.setOrgId(id);
    }

    /**
     * 设置登录机构名称
     *
     * @param name 机构名称
     */
    @JsonIgnore
    public void setOrgName(String name) {
        loginInfo.setOrgName(name);
    }

    /**
     * 验证Token是否合法
     *
     * @param hash 令牌哈希值
     * @return Token是否合法
     */
    @JsonIgnore
    public boolean verifyTokenHash(String hash) {
        return this.hash.equals(hash);
    }

    /**
     * 验证Token是否合法
     *
     * @param secretKey 令牌安全码
     * @return Token是否合法
     */
    @JsonIgnore
    public boolean verifySecretKey(String secretKey) {
        return this.secretKey.equals(secretKey);
    }

    /**
     * Token有效期是否过半
     *
     * @return 有效期是否过半
     */
    @JsonIgnore
    public boolean isHalfLife() {
        return getLife() > 0 && DateTime.getRemainSeconds(expiryTime) < life / 2;
    }

    /**
     * 授权是否过期
     *
     * @return 授权是否过期
     */
    @JsonIgnore
    public boolean isPermitExpiry() {
        if (getPermitLife() <= 0) {
            return false;
        }

        return LocalDateTime.now().isAfter(permitTime.plusSeconds(getPermitLife()));
    }

    /**
     * Token是否过期
     *
     * @return Token是否过期
     */
    @JsonIgnore
    public boolean isExpiry() {
        return getLife() > 0 && LocalDateTime.now().isAfter(expiryTime.plusSeconds(TIME_OUT));
    }

    /**
     * Token是否失效
     *
     * @return Token是否失效
     */
    @JsonIgnore
    public boolean isFailure() {
        return getLife() > 0 && LocalDateTime.now().isAfter(failureTime.plusSeconds(TIME_OUT));
    }
}
