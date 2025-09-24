package com.insight.utils.pojo.paper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/4/21
 * @remark
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaperGroup {

    /**
     * 题号
     */
    private String index;

    /**
     * 名称
     */
    private String name;

    /**
     * 习题组集合
     */
    private List<ProblemGroup> problems;

    /**
     * 是否显示名称
     */
    private Boolean showNameOperation;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getShowNameOperation() {
        return showNameOperation;
    }

    public void setShowNameOperation(Boolean showNameOperation) {
        this.showNameOperation = showNameOperation;
    }

    public List<ProblemGroup> getProblems() {
        return problems == null ? List.of() : problems;
    }

    public void setProblems(List<ProblemGroup> problems) {
        this.problems = problems;
    }

    @JsonIgnore
    public BigDecimal getPoint() {
        return Util.isEmpty(problems)
                ? BigDecimal.ZERO
                : problems.stream().map(ProblemGroup::getPoint).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @JsonIgnore
    public List<ProblemCore> getProblemList() {
        return Util.isEmpty(problems)
                ? new ArrayList<>()
                : problems.stream().flatMap(i -> i.getProblems().stream()).toList();
    }

    @JsonIgnore
    public Boolean hasErrors() {
        return Util.isEmpty(problems) || problems.stream().anyMatch(ProblemGroup::hasErrors);
    }
}

