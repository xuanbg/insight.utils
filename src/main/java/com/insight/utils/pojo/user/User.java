package com.insight.utils.pojo.user;

import com.insight.utils.Util;

import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2023/5/4
 * @remark
 */
public class User extends UserBase {

    /**
     * 登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 微信UnionID
     */
    private String unionId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户是否内置
     */
    private Boolean builtin;

    /**
     * 用户是否失效
     */
    private Boolean invalid;

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

    public String getAccount() {
        return Util.isEmpty(account) ? null : account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return Util.isEmpty(password) ? null : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return Util.isEmpty(password) ? null : payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getUnionId() {
        return Util.isEmpty(unionId) ? null : unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return Util.isEmpty(remark) ? null : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getBuiltin() {
        return builtin;
    }

    public void setBuiltin(Boolean builtin) {
        this.builtin = builtin;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
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
