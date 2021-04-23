package com.insight.utils.pojo;

/**
 * @author 宣炳刚
 * @date 2020/6/9
 * @remark 联系信息DTO
 */
public class ContactInfo extends BaseXo {

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机
     */
    private String contactMobile;

    /**
     * 联系人邮箱
     */
    private String contactMail;

    /**
     * 公司所在省/直辖市ID
     */
    private String provinceId;

    /**
     * 公司所在省/直辖市
     */
    private String province;

    /**
     * 公司所在市ID
     */
    private String cityId;

    /**
     * 公司所在市
     */
    private String city;

    /**
     * 公司所在区县ID
     */
    private String countyId;

    /**
     * 公司所在区县
     */
    private String county;

    /**
     * 详细地址
     */
    private String address;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
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
}
