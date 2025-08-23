package com.insight.utils.pojo.auth;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2025/8/23
 * @remark 第三方应用OpenID
 */
public class OpenId extends BaseXo {

    /**
     * 应用ID
     */
    private String appId;

    /**
     * OpenId
     */
    private String openId;

    /**
     * 服务类型
     */
    private String service;

    /**
     * 构造函数
     */
    public OpenId() {
    }

    /**
     * 构造函数
     *
     * @param appId    应用ID
     * @param openId   OpenId
     */
    public OpenId(String appId, String openId) {
        this.appId = appId;
        this.openId = openId;
    }

    /**
     * 构造函数
     *
     * @param appId    应用ID
     * @param openId   OpenId
     * @param service  服务类型
     */
    public OpenId(String appId, String openId, String service) {
        this.appId = appId;
        this.openId = openId;
        this.service = service;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean matches(OpenId data) {
        return this.appId.equals(data.appId) && (data.service == null || data.service.equals(this.service));
    }
}
