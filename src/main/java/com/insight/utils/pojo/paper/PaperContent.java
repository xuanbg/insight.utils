package com.insight.utils.pojo.paper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/4/18
 * @remark 试卷内容实体类
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaperContent extends BaseXo {

    /**
     * 类型: 0.文本, 1.试卷, 2.答题卡
     */
    private Integer type;

    /**
     * 富文本内容
     */
    private String html;

    /**
     * 标题
     */
    private String title;

    /**
     * 习题分组
     */
    private List<PaperGroup> groups;

    /**
     * 问题
     */
    private List<Object> questions;

    /**
     * 附件
     */
    private List<AttachFile> attachFiles;

    /**
     * 设置
     */
    private Object setting;

    public Integer getType() {
        return type == null ? 1 : type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PaperGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PaperGroup> groups) {
        this.groups = groups;
    }

    public List<Object> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Object> questions) {
        this.questions = questions;
    }

    public List<AttachFile> getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(List<AttachFile> attachFiles) {
        this.attachFiles = attachFiles;
    }

    public Object getSetting() {
        return setting;
    }

    public void setSetting(Object setting) {
        this.setting = setting;
    }

    /**
     * 获取总分
     *
     * @return 总分
     */
    @JsonIgnore
    public BigDecimal getPoint() {
        return Util.isEmpty(groups)
                ? BigDecimal.ZERO
                : groups.stream().map(PaperGroup::getPoint).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 获取所有习题组ID
     *
     * @return 所有分组ID
     */
    @JsonIgnore
    public List<Long> getGroupIds() {
        return groups == null ? List.of() : groups.stream().flatMap(g -> g.getProblems().stream()).map(ProblemGroup::getId).toList();
    }

    /**
     * 获取所有习题ID
     *
     * @return 所有习题ID
     */
    @JsonIgnore
    public List<Long> getProblemIds() {
        return getProblems().stream().map(ProblemCore::getId).toList();
    }

    /**
     * 获取所有习题
     *
     * @return 所有习题
     */
    @JsonIgnore
    public List<ProblemCore> getProblems() {
        return Util.isEmpty(groups)
                ? new ArrayList<>()
                : groups.stream().flatMap(i -> i.getProblemList().stream()).toList();
    }

    /**
     * 试卷内容是否有错误
     *
     * @return 是否有错误
     */
    @JsonIgnore
    public Boolean hasErrors() {
        return Util.isEmpty(groups) || groups.stream().anyMatch(PaperGroup::hasErrors);
    }
}
