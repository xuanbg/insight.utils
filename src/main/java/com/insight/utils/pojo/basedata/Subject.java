package com.insight.utils.pojo.basedata;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2021/11/30
 * @remark 学科VO
 */
public class Subject extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * XKW课程ID
     */
    private Long xkwId;

    /**
     * 学段
     */
    private String phase;

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

    public Long getXkwId() {
        return xkwId;
    }

    public void setXkwId(Long xkwId) {
        this.xkwId = xkwId;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
