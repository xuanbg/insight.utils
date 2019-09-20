package com.insight.util.pojo;

import com.insight.util.Json;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2019/9/20
 * @remark 自定义消息DTO
 */
public class CustomMessage implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 消息标签
     */
    private String tag;

    /**
     * 发送类型:0.未定义;1.仅消息(001);2.仅推送(010);3.推送+消息(011);4.仅短信(100);5.消息+短信(101);6.推送+短信(110);7.消息+推送+短信(111)
     */
    private Integer type;

    /**
     * 接收人,多个接收人使用逗号分隔
     */
    private String receivers;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送参数
     */
    private Map params;

    /**
     * 是否广播消息
     */
    private Boolean isBroadcast;

    /**
     * 消息失效日期
     */
    private Date expireDate;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
