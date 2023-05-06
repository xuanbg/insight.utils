package com.insight.utils.pojo.user;

import com.insight.utils.pojo.base.BaseXo;

import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2018/1/4
 * @remark 用户实体类
 */
public class UserBase extends BaseXo {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 用户编码
     */
    private String code;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户E-mail
     */
    private String email;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String headImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type == null ? 0 : type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Boolean mobileEquals(String mobile) {
        return Objects.equals(this.mobile, mobile);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var user = (UserBase) o;
        if (Objects.equals(code, user.code)) return true;
        if (Objects.equals(name, user.name)) return true;
        if (Objects.equals(mobile, user.mobile)) return true;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        var result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
