package com.insight.utils.pojo;

/**
 * @author xuan
 * @date 2017年9月06日
 * @remark 访问令牌，客户端验证身份所需的凭证
 */
public class AccessToken extends BaseXo {

    /**
     * 令牌ID，唯一
     */
    private String id;

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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}