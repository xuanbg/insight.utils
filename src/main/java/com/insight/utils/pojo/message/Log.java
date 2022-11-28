package com.insight.utils.pojo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insight.utils.pojo.base.BaseXo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2019-09-13
 * @remark 操作日志记录类
 */
public class Log extends BaseXo {

    /**
     * 日志ID
     */
    private Long id;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 类型
     */
    private OperateType type;

    /**
     * 关联业务名称
     */
    private String business;

    /**
     * 关联业务ID
     */
    private Long businessId;

    /**
     * 记录内容
     */
    private Object content;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public OperateType getType() {
        return type;
    }

    public void setType(OperateType type) {
        this.type = type;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
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
