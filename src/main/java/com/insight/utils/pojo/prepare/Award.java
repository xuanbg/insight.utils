package com.insight.utils.pojo.prepare;

import com.insight.utils.pojo.base.BaseXo;

import java.math.BigDecimal;

/**
 * @author 宣炳刚
 * @date 2024/12/6
 * @remark 课程奖励实体类
 */
public class Award extends BaseXo {

    /**
     * 测验时间(分)
     */
    private Integer testTime = 20;

    /**
     * 达标成绩
     */
    private BigDecimal standardScore = BigDecimal.valueOf(60);

    /**
     * 奖励学分
     */
    private BigDecimal credit = BigDecimal.valueOf(0.01);

    public Integer getTestTime() {
        return testTime;
    }

    public void setTestTime(Integer testTime) {
        this.testTime = testTime;
    }

    public BigDecimal getStandardScore() {
        return standardScore;
    }

    public void setStandardScore(BigDecimal standardScore) {
        this.standardScore = standardScore;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
}
