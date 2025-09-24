package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2024/12/29
 * @remark 思维导图
 */
public class MindMap extends BaseXo {

    /**
     * 元数据
     */
    private Object meta;

    /**
     * 格式
     */
    private String format;

    /**
     * 数据
     */
    private List<Object> data;

    /**
     * 原始数据
     */
    private List<Object> source;

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public void setSource(List<Object> source) {
        this.source = source;
    }

    @JsonIgnore
    public Long getCount() {
        return source == null || data == null ? null : data.stream()
                .filter(i -> source.stream().noneMatch(i::equals)).count();
    }
}
