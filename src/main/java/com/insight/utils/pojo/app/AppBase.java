package com.insight.utils.pojo.app;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2017/9/30
 * @remark 应用实体类
 */
public class AppBase extends BaseXo {

    /**
     * 应用ID
     */
    private Long id;

    /**
     * 授权码生命周期(秒)
     */
    private Long permitLife;

    /**
     * 令牌生命周期(秒)
     */
    private Long tokenLife;

    /**
     * 验证来源
     */
    private Boolean verifySource;

    /**
     * 是否单点登录
     */
    private Boolean signinOne;

    /**
     * 是否自动刷新令牌
     */
    private Boolean autoRefresh;

    /**
     * 是否平台应用: 0.租户应用, 1.平台应用
     */
    private Boolean platform;

    /**
     * 是否限定用户类型
     */
    private Integer limitType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermitLife() {
        return permitLife == null ? 5 : permitLife;
    }

    public void setPermitLife(Long permitLife) {
        this.permitLife = permitLife;
    }

    public Long getTokenLife() {
        return tokenLife == null ? 7200 : tokenLife;
    }

    public void setTokenLife(Long tokenLife) {
        this.tokenLife = tokenLife;
    }

    public Boolean getVerifySource() {
        return verifySource != null && verifySource;
    }

    public void setVerifySource(Boolean verifySource) {
        this.verifySource = verifySource;
    }

    public Boolean getSigninOne() {
        return signinOne != null && signinOne;
    }

    public void setSigninOne(Boolean signinOne) {
        this.signinOne = signinOne;
    }

    public Boolean getAutoRefresh() {
        return autoRefresh != null && autoRefresh;
    }

    public void setAutoRefresh(Boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public Boolean getPlatform() {
        return platform;
    }

    public void setPlatform(Boolean platform) {
        this.platform = platform;
    }

    public Integer getLimitType() {
        return limitType;
    }

    public void setLimitType(Integer limitType) {
        this.limitType = limitType;
    }
}
