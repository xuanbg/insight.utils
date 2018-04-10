package com.insight.util.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author duxl
 * @date 2017/9/7
 * @remark account
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * UUID主键
     */
    private String id;

    /**
     * 账户类型：0、未定义；1、旅行社；2、供应商；3、旅行社和供应商
     */
    private Integer accountType;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 别名
     */
    private String alias;

    /**
     * 企业LOGO
     */
    private String logo;

    /**
     * 公司所在省/直辖市
     */
    private String province;

    /**
     * 公司所在市
     */
    private String city;

    /**
     * 公司所在区县
     */
    private String county;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 营业执照号码
     */
    private String license;

    /**
     * 营业执照照片URL
     */
    private String licenseImage;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机
     */
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    private String contactMailbox;

    /**
     * 负责BD姓名
     */
    private String bdCharger;

    /**
     * 负责客服姓名
     */
    private String csCharger;

    /**
     * 描述
     */
    private String description;

    /**
     * 账户状态：0、待审核；1、已通过；2、未通过
     */
    private Integer accountStatus;

    /**
     * 是否失效：0、正常；1、失效
     */
    private Boolean isInvalid;

    /**
     * 审核人ID
     */
    private String auditUserId;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 创建人ID
     */
    private String creatorUserId;

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

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(String licenseImage) {
        this.licenseImage = licenseImage;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactMailbox() {
        return contactMailbox;
    }

    public void setContactMailbox(String contactMailbox) {
        this.contactMailbox = contactMailbox;
    }

    public String getBdCharger() {
        return bdCharger;
    }

    public void setBdCharger(String bdCharger) {
        this.bdCharger = bdCharger;
    }

    public String getCsCharger() {
        return csCharger;
    }

    public void setCsCharger(String csCharger) {
        this.csCharger = csCharger;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Boolean getInvalid() {
        return isInvalid;
    }

    public void setInvalid(Boolean invalid) {
        isInvalid = invalid;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
