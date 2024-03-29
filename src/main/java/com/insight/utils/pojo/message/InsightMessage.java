package com.insight.utils.pojo.message;

import com.insight.utils.pojo.base.BaseXo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2019/9/20
 * @remark 消息DTO
 */
public class InsightMessage extends BaseXo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 消息类型:0.未定义;1.仅消息(0001);2.仅推送(0010);3.推送+消息(0011);4.仅短信(0100);8.仅邮件(1000)
     */
    private Integer type;

    /**
     * 通道
     */
    private String channel;

    /**
     * 接收人，用户ID(推送)/手机号(短信)
     */
    private List<String> receivers;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 推送参数
     */
    private Map<String, Object> params;

    /**
     * 消息标签
     */
    private String tag;

    /**
     * 有效时长(分钟)
     */
    private Integer expire;

    /**
     * 是否广播消息
     */
    private Boolean isBroadcast;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 设置单个消息接收人
     *
     * @param receiver 消息接收人
     */
    public void setReceiver(String receiver) {
        receivers = new ArrayList<>();
        receivers.add(receiver);
    }

    /**
     * 设置单参数
     *
     * @param key   键名
     * @param value 键值
     */
    public void setParam(String key, Object value) {
        params = new HashMap<>(4);
        params.put(key, value);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getExpire() {
        return expire == null ? 5 : expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public Boolean getBroadcast() {
        return isBroadcast != null && isBroadcast;
    }

    public void setBroadcast(Boolean broadcast) {
        isBroadcast = broadcast;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
