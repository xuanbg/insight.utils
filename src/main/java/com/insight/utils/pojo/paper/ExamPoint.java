package com.insight.utils.pojo.paper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;

import java.math.BigDecimal;

/**
 * @author 宣炳刚
 * @date 2021/8/17
 * @remark 考点得分类
 */
public class ExamPoint extends BaseXo {

    /**
     * 得分点
     */
    private String name;

    /**
     * 得分
     */
    private BigDecimal point;

    /**
     * 是否得分
     */
    private Boolean gain;

    /**
     * 构造函数
     */
    public ExamPoint() {
    }

    /**
     * 构造函数
     *
     * @param name  考点名称
     * @param point 得分
     */
    public ExamPoint(String name, BigDecimal point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPoint() {
        return point == null ? BigDecimal.ZERO : point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Boolean getGain() {
        return gain;
    }

    public void setGain(Boolean gain) {
        this.gain = gain;
    }

    @JsonIgnore
    public Boolean notExamined() {
        return gain == null;
    }

    @JsonIgnore
    public Boolean isCorrect() {
        return gain != null && gain;
    }

    @JsonIgnore
    public BigDecimal getScore() {
        return gain != null && gain ? getPoint() : BigDecimal.ZERO;
    }

    @JsonIgnore
    public Boolean showExamPoint() {
        return Util.isNotEmpty(name) && !"分值|分数|得分点".contains(name.trim());
    }
}
