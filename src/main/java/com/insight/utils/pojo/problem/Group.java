package com.insight.utils.pojo.problem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.base.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2023/4/21
 * @remark 习题组
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Group extends BaseXo {

    /**
     * 习题组ID
     */
    private Long id;

    /**
     * 学科ID
     */
    private Long subjectId;

    /**
     * 学科网习题ID
     */
    private Long xkwId;

    /**
     * 学校名称
     */
    private String school;

    /**
     * 标题
     */
    private String caption;

    /**
     * 习题集合
     */
    private List<Problem> problems = new ArrayList<>();

    /**
     * 教学目标集合
     */
    private List<DataBase> objects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getXkwId() {
        return xkwId;
    }

    public void setXkwId(Long xkwId) {
        this.xkwId = xkwId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<DataBase> getObjects() {
        return objects;
    }

    public void setObjects(List<DataBase> objects) {
        this.objects = objects;
    }

    /**
     * 添加习题
     *
     * @param problem 习题
     */
    @JsonIgnore
    public void addProblem(Problem problem) {
        problems.add(problem);
    }

    /**
     * 判断当前习题组是否为指定习题组
     *
     * @param xkwId 习题组ID
     * @return 布尔值
     */
    @JsonIgnore
    public Boolean equals(Long xkwId) {
        return Objects.equals(this.xkwId, xkwId);
    }
}

