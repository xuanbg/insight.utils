package com.insight.utils.pojo.problem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2025/9/23
 * @remark 试题数据, 用于题库
 */
public class Problem extends ProblemBase {

    /**
     * 得分点
     */
    private List<String> examPoint;

    /**
     * 试题难度系数
     */
    private BigDecimal difficulty;

    /**
     * 答题基准时间(秒)
     */
    private Integer baseTime;

    /**
     * 认知要求: 1.记忆, 2.理解, 3.应用, 4.分析, 5.综合运用
     */
    private Integer level;

    public List<String> getExamPoint() {
        return examPoint;
    }

    public void setExamPoint(List<String> examPoint) {
        this.examPoint = examPoint;
    }

    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getBaseTime() {
        return baseTime != null && baseTime > 10 * getLevel() ? baseTime : (int) ((baseTime == null ? 10 : baseTime) * 3 * Math.sqrt(getLevel()));
    }

    public void setBaseTime(Integer baseTime) {
        this.baseTime = baseTime;
    }

    public Integer getLevel() {
        return level == null ? 2 : level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
