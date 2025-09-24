package com.insight.utils.xkw;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

import java.util.Collection;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2025/9/1
 * @remark
 */
public class PaperBody extends BaseXo {

    /**
     * 试卷内容头
     */
    @JsonAlias("part_head")
    private PartHead partHead;

    /**
     * 题型列表及试题
     */
    @JsonAlias("part_body")
    private List<PartBody> partBody;

    public PartHead getPartHead() {
        return partHead;
    }

    public void setPartHead(PartHead partHead) {
        this.partHead = partHead;
    }

    public List<PartBody> getPartBody() {
        return partBody;
    }

    public void setPartBody(List<PartBody> partBody) {
        this.partBody = partBody;
    }

    /**
     * 获取所有试题
     *
     * @return 试题列表
     */
    @JsonIgnore
    public List<Question> getQuestions() {
        return partBody == null ? List.of() : partBody.stream().flatMap(i -> i.getQuestions().stream()).toList();
    }

    /**
     * 获取所有试题组
     *
     * @return 试题组列表
     */
    @JsonIgnore
    public List<GroupDto> getProblems() {
        return partBody == null ? List.of() : partBody.stream().map(PartBody::getProblems).flatMap(Collection::stream).toList();
    }
}
