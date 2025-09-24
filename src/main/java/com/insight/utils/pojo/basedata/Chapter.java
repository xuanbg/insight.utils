package com.insight.utils.pojo.basedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2021/11/30
 * @remark 章节实体类
 */
public class Chapter extends BaseXo {

    /**
     * 章节ID
     */
    private Long id;

    /**
     * 父章节ID
     */
    private Long parentId;

    /**
     * 教材ID
     */
    private Long textbookId;

    /**
     * 章节名称
     */
    private String name;

    /**
     * XKWID
     */
    private Long xkwId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(Long textbookId) {
        this.textbookId = textbookId;
    }

    public Long getXkwId() {
        return xkwId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setXkwId(Long xkwId) {
        this.xkwId = xkwId;
    }

    @JsonIgnore
    public Boolean match(Long parentId, String name) {
        return Objects.equals(this.parentId, parentId) && Objects.equals(this.name, name);
    }

    @JsonIgnore
    public Boolean matchXkwId(Long xkwId) {
        return Objects.equals(this.xkwId, xkwId);
    }
}
