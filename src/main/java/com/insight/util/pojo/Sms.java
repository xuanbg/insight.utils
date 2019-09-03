package com.insight.util.pojo;

import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2019-08-31
 * @remark 短信DTO
 */
public class Sms {

    /**
     * 手机号,多个手机号逗号分隔
     */
    private String mobiles;

    /**
     * 场景编号
     */
    private String scene;

    /**
     * 渠道编号
     */
    private String channel;

    /**
     * 短信参数
     */
    private Map param;

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Map getParam() {
        return param;
    }

    public void setParam(Map param) {
        this.param = param;
    }
}
