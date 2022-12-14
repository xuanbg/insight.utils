package com.insight.utils.pojo.message;

import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.base.BusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2019-09-13
 * @remark 短信验证码DTO
 */
public class SmsCode extends BaseXo {

    /**
     * 通道
     */
    private String channel;

    /**
     * 验证码类型:0.验证手机号;1.注册用户账号;2.重置密码;3.修改支付密码;4.修改手机号
     */
    private Integer type;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码有效时间(分钟)
     */
    private Integer minutes;

    /**
     * 验证码长度
     */
    private Integer lenght;

    public Map<String, Object> getParam() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", getCode());
        map.put("minutes", getMinutes());

        return map;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        if (Util.isEmpty(mobile)) {
            throw new BusinessException("手机号不能为空");
        }

        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        if (Util.isEmpty(code)) {
            code = Util.randomString(getLenght());
        }

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMinutes() {
        return minutes == null ? 5 : minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getLenght() {
        return lenght == null ? 6 : lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }
}
