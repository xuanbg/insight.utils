package com.insight.utils.pojo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.DateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2018/1/4
 * @remark 令牌信息实体类
 */
public class TokenData extends TokenKey {
    public static final int TIME_OUT = 300;

    /**
     * 用户特征字符串
     */
    private String fingerprint;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 机构ID
     */
    private Long orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 区号
     */
    private String areaCode;

    /**
     * 授权码生命周期(秒)
     */
    private Long permitLife;

    /**
     * 授权码获取时间
     */
    private LocalDateTime permitTime;

    /**
     * 令牌生命周期(秒)
     */
    private Long life;

    /**
     * Token过期时间
     */
    private LocalDateTime expiryTime;

    /**
     * 功能授权代码集合
     */
    private List<String> permitFuncs;

    /**
     * 是否自动刷新
     */
    private Boolean autoRefresh;

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getPermitLife() {
        return permitLife == null ? 0L : permitLife;
    }

    public void setPermitLife(Long permitLife) {
        this.permitLife = permitLife;
    }

    public LocalDateTime getPermitTime() {
        return permitTime;
    }

    public void setPermitTime(LocalDateTime permitTime) {
        this.permitTime = permitTime;
    }

    public Long getLife() {
        return life == null ? 0L : life;
    }

    public void setLife(Long life) {
        this.life = life;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public List<String> getPermitFuncs() {
        return permitFuncs == null ? new ArrayList<>() : permitFuncs;
    }

    public void setPermitFuncs(List<String> permitFuncs) {
        this.permitFuncs = permitFuncs;
    }

    public Boolean getAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(Boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    /**
     * 验证Token是否合法
     *
     * @param secret 令牌安全码
     * @return 是否合法
     */
    @JsonIgnore
    public boolean verify(String secret) {
        return Objects.equals(this.secret, secret);
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
     * Token是否过期
     *
     * @return 是否过期
     */
    @JsonIgnore
    public boolean isExpiry() {
        return getLife() > 0 && LocalDateTime.now().isAfter(expiryTime.plusSeconds(TIME_OUT));
    }

    /**
     * 授权是否过期
     *
     * @return 是否过期
     */
    @JsonIgnore
    public boolean isPermitExpiry() {
        if (getPermitLife() <= 0) {
            return false;
        }

        return LocalDateTime.now().isAfter(permitTime.plusSeconds(getPermitLife()));
    }

    /**
     * 指纹是否相同
     *
     * @param fingerprint 指纹
     * @return 是否相同
     */
    @JsonIgnore
    public boolean fingerprintIsMatch(String fingerprint) {
        return Objects.equals(this.fingerprint, fingerprint);
    }
}
