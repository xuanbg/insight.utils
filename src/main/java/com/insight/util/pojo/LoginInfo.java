package com.insight.util.pojo;

import com.insight.util.Json;

import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2018/1/4
 * @remark 用户登录信息实体类
 */
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String headImg;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return Json.toBase64(this);
    }
}
