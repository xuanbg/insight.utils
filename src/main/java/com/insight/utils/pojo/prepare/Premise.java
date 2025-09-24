package com.insight.utils.pojo.prepare;

import com.insight.utils.pojo.base.BaseXo;

import java.math.BigDecimal;

/**
 * @author 宣炳刚
 * @date 2024/12/5
 * @remark 课程前提实体类
 */
public class Premise extends BaseXo {

    /**
     * 前置课程ID
     */
    private Long lessonId;

    /**
     * 达成学分
     */
    private BigDecimal needCredit;

    /**
     * 是否强制
     */
    private Boolean force;

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public BigDecimal getNeedCredit() {
        return needCredit;
    }

    public void setNeedCredit(BigDecimal needCredit) {
        this.needCredit = needCredit;
    }

    public Boolean getForce() {
        return force != null && force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }
}
