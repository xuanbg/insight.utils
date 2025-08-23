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

    public Boolean matches(String appId, String service) {
        return this.appId.equals(appId) && (service == null || service.equals(this.service));
    }
}
