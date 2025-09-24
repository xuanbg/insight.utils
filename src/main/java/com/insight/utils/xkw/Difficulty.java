package com.insight.utils.xkw;

import com.insight.utils.pojo.base.BaseXo;

import java.math.BigDecimal;

/**
 * @author 宣炳刚
 * @date 2025/9/1
 * @remark
 */
public class Difficulty extends BaseXo {

    /**
     * 主键
     */
    private long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 难度区间上限
     */
    private BigDecimal ceiling;

    /**
     * 难度区间下限
     */
    private BigDecimal flooring;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCeiling() {
        return ceiling;
    }

    public void setCeiling(BigDecimal ceiling) {
        this.ceiling = ceiling;
    }

    public BigDecimal getFlooring() {
        return flooring;
    }

    public void setFlooring(BigDecimal flooring) {
        this.flooring = flooring;
    }
}
