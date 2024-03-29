package com.insight.utils.pojo.message;

import com.insight.utils.pojo.base.BaseXo;

import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2019/9/23
 * @remark 计划任务DTO
 */
public class Schedule<T> extends BaseXo {

    /**
     * UUID主键
     */
    private Long id;

    /**
     * 任务类型:0.消息;1.本地调用;2.远程调用
     */
    private Integer type;

    /**
     * 调用方法
     */
    private String method;

    /**
     * 任务执行时间
     */
    private LocalDateTime taskTime;

    /**
     * 任务内容
     */
    private T content;

    /**
     * 累计执行次数
     */
    private Integer count;

    /**
     * 任务过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 是否失效
     */
    private Boolean isInvalid;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(LocalDateTime taskTime) {
        this.taskTime = taskTime;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getInvalid() {
        return isInvalid;
    }

    public void setInvalid(Boolean invalid) {
        isInvalid = invalid;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
