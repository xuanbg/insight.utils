package com.insight.utils.pojo;

import com.insight.utils.Json;

import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2017/9/15
 * @remark 模块信息
 */
public class ModuleInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 图标路径
     */
    private String iconUrl;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 模块文件
     */
    private String file;

    /**
     * 是否启动模块
     */
    private Boolean isAutoLoad;

    /**
     * 是否具有选项
     */
    private Boolean hasParams;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Boolean getAutoLoad() {
        return isAutoLoad;
    }

    public void setAutoLoad(Boolean autoLoad) {
        isAutoLoad = autoLoad;
    }

    public Boolean getHasParams() {
        return hasParams;
    }

    public void setHasParams(Boolean hasParams) {
        this.hasParams = hasParams;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
