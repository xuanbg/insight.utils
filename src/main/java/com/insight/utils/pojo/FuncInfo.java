package com.insight.utils.pojo;

import com.insight.utils.Json;

import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2017/9/15
 * @remark 功能信息
 */
public class FuncInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 图标路径
     */
    private String iconUrl;

    /**
     * 是否开始分组
     */
    private Boolean isBeginGroup;

    /**
     * 是否隐藏文字
     */
    private Boolean isHideText;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Boolean getBeginGroup() {
        return isBeginGroup;
    }

    public void setBeginGroup(Boolean beginGroup) {
        isBeginGroup = beginGroup;
    }

    public Boolean getHideText() {
        return isHideText;
    }

    public void setHideText(Boolean hideText) {
        isHideText = hideText;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
