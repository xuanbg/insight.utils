package com.insight.util.pojo;

import com.insight.util.Json;

import java.io.Serializable;

/**
 * @author xuan
 * @date 2017年9月06日
 * @remark 访问令牌，客户端验证身份所需的凭证
 */
public class AccessToken implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 令牌ID，唯一
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 安全码，用于验证令牌合法性
     */
    private String secret;

    public AccessToken() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}