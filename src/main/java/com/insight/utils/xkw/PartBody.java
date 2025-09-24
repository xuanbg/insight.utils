package com.insight.utils.xkw;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

import java.util.List;
import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2025/9/1
 * @remark
 */
public class PartBody extends BaseXo {

    /**
     * 题组
     */
    private PartHead type;

    /**
     * 习题列表
     */
    private List<Question> questions;

    public PartHead getType() {
        return type;
    }

    public void setType(PartHead type) {
        this.type = type;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * 获取所有试题
     *
     * @return 试题列表
     */
    @JsonIgnore
    public List<GroupDto> getProblems() {
        return questions == null ? List.of() : questions.stream().filter(Objects::nonNull).map(GroupDto::new).toList();
    }
}
