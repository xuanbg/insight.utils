package com.insight.utils.pojo.paper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Json;
import com.insight.utils.Util;
import com.insight.utils.pojo.problem.ProblemBase;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public List<ExamPoint> getExamPoint() {
        return examPoint;
    }

    public void setExamPoint(Object examPoint) {
        if (examPoint instanceof List<?>) {
            this.examPoint = new ArrayList<>();
            var list = (List) examPoint;
            if (list.isEmpty()) {
                this.examPoint.add(new ExamPoint("分值", BigDecimal.valueOf(5)));
            } else {
                var firstElement = list.get(0);
                if (firstElement instanceof String) {
                    for (Object item : list) {
                        this.examPoint.add(new ExamPoint(item.toString(), BigDecimal.valueOf(5)));
                    }
                } else {
                    for (Object item : list) {
                        this.examPoint.add(Json.toBean(item, ExamPoint.class));
                    }
                }
            }
        } else {
            this.examPoint = List.of(new ExamPoint("分值", BigDecimal.valueOf(5)));
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
     * 是否未批改
     *
     * @return 是否未批改
     */
    public Boolean notExamined() {
        return Util.isEmpty(examPoint) || examPoint.stream().anyMatch(ExamPoint::notExamined);
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

    /**
     * 试题是否包含错误
     *
     * @return 是否包含错误
     */
    @JsonIgnore
    public Boolean hasErrors() {
        return Util.isEmpty(examPoint);
    }
}
