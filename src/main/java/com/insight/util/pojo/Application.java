package com.insight.util.pojo;

import com.insight.util.Json;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2017/9/30
 * @remark 应用实体类
 */
public class Application implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 应用ID
     */
    private String id;

    /**
     * 序号
     */
    private Integer index;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用简称
     */
    private String alias;

    /**
     * 应用图标
     */
    private String icon;

    /**
     * 应用域名
     */
    private String domain;

    /**
     * 授权码生命周期(毫秒)
     */
    private Long permitLife;

    /**
     * 令牌生命周期(毫秒)
     */
    private Long tokenLife;

    /**
     * 是否单点登录
     */
    private Boolean isSigninOne;

    /**
     * 是否自动刷新令牌
     */
    private Boolean isAutoRefresh;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getPermitLife() {
        return permitLife;
    }

    public void setPermitLife(Long permitLife) {
        this.permitLife = permitLife;
    }

    public Long getTokenLife() {
        return tokenLife;
    }

    public void setTokenLife(Long tokenLife) {
        this.tokenLife = tokenLife;
    }

    public Boolean getSigninOne() {
        return isSigninOne;
    }

    public void setSigninOne(Boolean signinOne) {
        isSigninOne = signinOne;
    }

    public Boolean getAutoRefresh() {
        return isAutoRefresh;
    }

    public void setAutoRefresh(Boolean autoRefresh) {
        isAutoRefresh = autoRefresh;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
