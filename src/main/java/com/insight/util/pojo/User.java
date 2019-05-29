package com.insight.util.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 宣炳刚
 * @date 2018/1/4
 * @remark 用户实体类
 */
public class User implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户编码
     */
    private String code;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户E-mail
     */
    private String email;

    /**
     * 微信UnionID
     */
    private String unionId;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户是否内置
     */
    private Boolean builtIn;

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
    private String creatorId;

    /**
     * 创建时间
     */
    private Date createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getBuiltIn() {
        return builtIn;
    }

    public void setBuiltIn(Boolean builtIn) {
        this.builtIn = builtIn;
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
