package com.insight.utils.pojo.paper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Json;
import com.insight.utils.Util;
import com.insight.utils.pojo.problem.ProblemBase;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2021/7/16
 * @remark 试题类, 用于试卷
 */
public class ProblemCore extends ProblemBase {

    /**
     * 题号
     */
    private String index;

    /**
     * 题型名称
     */
    private String species;

    /**
     * 题型类型: 0.主观题, 1.单选题, 2.多选题, 3.是非题, 4.客观填空题
     */
    private Integer speciesType;

    /**
     * 标题
     */
    private String caption;

    /**
     * 得分点
     */
    private List<ExamPoint> examPoint;

    @Override
    public Long getId() {
        return super.getId() == null ? getOrder() : super.getId();
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getSpeciesType() {
        return speciesType == null ? 0 : speciesType;
    }

    public void setSpeciesType(Integer speciesType) {
        this.speciesType = speciesType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<ExamPoint> getExamPoint() {
        return examPoint;
    }

    public void setExamPoint(Object examPoint) {
        if (examPoint instanceof ExamPoint) {
            this.examPoint = List.of((ExamPoint) examPoint);
        } else {
            this.examPoint = Json.toList(examPoint, ExamPoint.class);
        }
    }

    /**
     * 获取试题顺序
     *
     * @return 试题顺序
     */
    @JsonIgnore
    public Integer getOrder() {
        if (Util.isEmpty(index)) {
            return 0;
        }

        if (!index.contains(".")) {
            return Integer.parseInt(index) * 10000;
        }

        var split0 = index.indexOf(".");
        var split1 = index.indexOf(".", split0 + 1);

        if (split1 < 0) {
            return Integer.parseInt(index.substring(0, split0)) * 10000 + Integer.parseInt(index.substring(split0 + 1)) * 100;
        } else {
            return Integer.parseInt(index.substring(0, split0)) * 10000 + Integer.parseInt(index.substring(split0 + 1, split1)) * 100 + Integer.parseInt(index.substring(split1 + 1));
        }
    }

    /**
     * 获取试题总分
     *
     * @return 试题总分
     */
    @JsonIgnore
    public BigDecimal getPoint() {
        return examPoint == null ? BigDecimal.valueOf(5) : examPoint.stream().map(ExamPoint::getPoint).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @JsonIgnore
    public Boolean hasErrors() {
       return Util.isEmpty(examPoint);
    }
}
