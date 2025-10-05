package com.insight.utils.pojo.problem;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.insight.utils.Util;
import com.insight.utils.common.MultiDateDeserializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2025/9/23
 * @remark 试题数据, 用于题库
 */
public class Problem extends ProblemBase {

    /**
     * 租户ID
     */
    private Long tenantId;

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

    /**
     * 是否原创
     */
    private Boolean original;

    /**
     * 是否范例
     */
    private Boolean example;

    /**
     * 评价得分
     */
    private BigDecimal score;

    /**
     * 试题备注
     */
    private String remark;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 更新人ID
     */
    private Long updaterId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = MultiDateDeserializer.class)
    private LocalDateTime createdTime;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public List<String> getExamPoint() {
        return Util.isEmpty(examPoint) ? List.of("分值") : examPoint;
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

    public Boolean getOriginal() {
        return original;
    }

    public void setOriginal(Boolean original) {
        this.original = original;
    }

    public Boolean getExample() {
        return example;
    }

    public void setExample(Boolean example) {
        this.example = example;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
