package com.insight.utils.pojo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2019-08-29
 * @remark 接口配置信息
 */
public class InterfaceDto extends BaseXo {

    /**
     * 接口HTTP请求方法
     */
    private String method;

    /**
     * 接口URL
     */
    private String url;

    /**
     * 接口URL正则表达式
     */
    private String regular;

    /**
     * 接口授权码
     */
    private String authCode;

    /**
     * 访问最小时间间隔(秒),0表示无调用时间间隔
     */
    private Integer limitGap;

    /**
     * 限流周期(秒),null表示不进行周期性限流
     */
    private Integer limitCycle;

    /**
     * 限制次数/限流周期,null表示不进行周期性限流
     */
    private Integer limitMax;

    /**
     * 限流消息
     */
    private String message;

    /**
     * 是否需要一次性Token
     */
    private Boolean needToken;

    /**
     * 是否验证Token
     */
    private Boolean verify;

    /**
     * 是否限流
     */
    private Boolean limit;

    /**
     * 是否通过日志输出返回值
     */
    private Boolean logResult;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Integer getLimitGap() {
        return limitGap == null ? 0 : limitGap;
    }

    public void setLimitGap(Integer limitGap) {
        this.limitGap = limitGap;
    }

    public Integer getLimitCycle() {
        return limitCycle == null ? 0 : limitCycle;
    }

    public void setLimitCycle(Integer limitCycle) {
        this.limitCycle = limitCycle;
    }

    public Integer getLimitMax() {
        return limitMax == null ? 0 : limitMax;
    }

    public void setLimitMax(Integer limitMax) {
        this.limitMax = limitMax;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getNeedToken() {
        return needToken != null && needToken;
    }

    public void setNeedToken(Boolean needToken) {
        this.needToken = needToken;
    }

    public Boolean getVerify() {
        return verify != null && verify;
    }

    public void setVerify(Boolean verify) {
        this.verify = verify;
    }

    public Boolean getLimit() {
        return limit != null && limit;
    }

    public void setLimit(Boolean limit) {
        this.limit = limit;
    }

    public Boolean getLogResult() {
        return logResult != null && logResult;
    }

    public void setLogResult(Boolean logResult) {
        this.logResult = logResult;
    }

    @JsonIgnore
    public String getHash() {
        return Util.md5(method + ":" + url);
    }
}
