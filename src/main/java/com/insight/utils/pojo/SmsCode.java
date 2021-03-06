package com.insight.utils.pojo;

import com.insight.utils.Json;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2019-09-13
 * @remark 短信验证码DTO
 */
public class SmsCode implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 验证码类型:0.验证手机号;1.注册用户账号;2.重置密码;3.修改支付密码;4.修改手机号
     */
    @NotNull(message = "验证码类型不能为空")
    private Integer type;

    /**
     * 手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    /**
     * 验证码长度
     */
    @NotNull(message = "验证码长度不能为空")
    private Integer length;

    /**
     * 验证码有效时间(分钟)
     */
    @NotNull(message = "验证码有效时间不能为空")
    private Integer minutes;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
