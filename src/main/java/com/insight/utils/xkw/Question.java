package com.insight.utils.xkw;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.insight.utils.common.MultiDateDeserializer;
import com.insight.utils.pojo.base.BaseXo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2025/9/1
 * @remark
 */
public class Question extends BaseXo {

    /**
     * 习题ID
     */
    private Long id;

    /**
     * 类型ID
     */
    @JsonAlias("type_id")
    private Long typeId;

    /**
     * 教材目录ID列表
     */
    @JsonAlias("catalog_ids")
    private List<Long> catalogIds;

    /**
     * 知识点ID列表
     */
    @JsonAlias("kpoint_ids")
    private List<Long> kpointIds;

    /**
     * 地区ID列表
     */
    @JsonAlias("area_ids")
    private List<Long> areaIds;

    /**
     * 年级ID列表
     */
    @JsonAlias("grade_ids")
    private List<Long> gradeIds;

    /**
     * 题干
     */
    private String stem;

    /**
     * 答案
     */
    private String answer;

    /**
     * 解析
     */
    private String explanation;

    /**
     * 分数
     */
    private List<Score> score;

    /**
     * 难度等级
     */
    @JsonAlias("difficulty_level")
    private Difficulty difficultyLevel;

    /**
     * 难度系数
     */
    private BigDecimal difficulty;

    /**
     * 来源
     */
    private String source;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 更新时间
     */
    @JsonDeserialize(using = MultiDateDeserializer.class)
    @JsonAlias("update_date")
    private LocalDateTime updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public List<Long> getCatalogIds() {
        return catalogIds;
    }

    public void setCatalogIds(List<Long> catalogIds) {
        this.catalogIds = catalogIds;
    }

    public List<Long> getKpointIds() {
        return kpointIds;
    }

    public void setKpointIds(List<Long> kpointIds) {
        this.kpointIds = kpointIds;
    }

    public List<Long> getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(List<Long> areaIds) {
        this.areaIds = areaIds;
    }

    public List<Long> getGradeIds() {
        return gradeIds;
    }

    public void setGradeIds(List<Long> gradeIds) {
        this.gradeIds = gradeIds;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<Score> getScore() {
        return score;
    }

    public void setScore(List<Score> score) {
        this.score = score;
    }

    public Difficulty getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Difficulty difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
