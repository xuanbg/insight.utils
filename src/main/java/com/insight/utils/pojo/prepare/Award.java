package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.base.BusinessException;

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
    private Integer testTime;

    /**
     * 达标成绩
     */
    private BigDecimal standardScore;

    /**
     * 奖励学分
     */
    private BigDecimal credit;

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

    @JsonIgnore
    public void check() {
        if (testTime == null || testTime == 0) {
            throw new BusinessException("请在课程设置中设置测验时间");
        }

        if (standardScore == null || standardScore.compareTo(BigDecimal.ZERO) == 0) {
            throw new BusinessException("请在课程设置中设置达标成绩");
        }
    }
}
