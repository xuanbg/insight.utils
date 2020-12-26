package com.insight.utils.pojo;

import com.insight.utils.Json;

import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark 选项参数DTO
 */
public class ParameterDto implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 唯一ID
     */
    private String id;

    /**
     * 配置KEY
     */
    private String key;

    /**
     * 配置键值
     */
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
