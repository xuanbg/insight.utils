package com.insight.utils.pojo.app;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2017/9/15
 * @remark 功能信息
 */
public class FuncInfo extends BaseXo {

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
}
