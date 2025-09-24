package com.insight.utils.pojo.basedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2021/11/30
 * @remark 教材版本VO
 */
public class Version extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 学科ID
     */
    private Long subjectId;

    /**
     * XKW教材版本ID
     */
    private Long xkwId;

    /**
     * 名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getXkwId() {
        return xkwId;
    }

    public void setXkwId(Long xkwId) {
        this.xkwId = xkwId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Boolean nameMatch(String name) {
        return Objects.equals(this.name, name);
    }

    @JsonIgnore
    public Boolean subjectMatch(Long subjectId) {
        return Objects.equals(this.subjectId, subjectId);
    }
}
