package com.insight.util.pojo;

import com.insight.util.Json;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2019/9/20
 * @remark 标准消息DTO
 */
public class NormalMessage implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 场景编码
     */
    @NotEmpty(message = "场景编码不能为空")
    private String sceneCode;

    /**
     * 应用ID
     */
    @NotEmpty(message = "应用ID不能为空")
    private String appId;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 接收人,多个接收人使用逗号分隔
     */
    @NotEmpty(message = "接收人不能为空")
    private String receivers;

    /**
     * 自定义参数
     */
    private Map params;

    /**
     * 是否广播消息
     */
    private Boolean isBroadcast;

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Boolean getBroadcast() {
        return isBroadcast;
    }

    public void setBroadcast(Boolean broadcast) {
        isBroadcast = broadcast;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
