package com.insight.utils.pojo.paper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/4/21
 * @remark
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProblemGroup extends BaseXo {

    /**
     * 习题组ID
     */
    private Long id;

    /**
     * 题号
     */
    private String index;

    /**
     * 习题集合
     */
    private List<ProblemCore> problems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<ProblemCore> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemCore> problems) {
        this.problems = problems;
    }

    @JsonIgnore
    public BigDecimal getPoint() {
        return Util.isEmpty(problems)
                ? BigDecimal.ZERO
                : problems.stream().map(ProblemCore::getPoint).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @JsonIgnore
    public Boolean hasErrors() {
        return Util.isEmpty(problems) || problems.stream().anyMatch(ProblemCore::hasErrors);
    }
}

