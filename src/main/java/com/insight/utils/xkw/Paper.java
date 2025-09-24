package com.insight.utils.xkw;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

import java.util.Collection;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2025/8/28
 * @remark
 */
public class Paper extends BaseXo {

    /**
     * 课程ID
     */
    @JsonAlias("course_id")
    private Long courseId;

    /**
     * 试卷头部信息
     */
    private PaperHead head;

    /**
     * 试卷内容
     */
    private List<PaperBody> body;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public PaperHead getHead() {
        return head;
    }

    public void setHead(PaperHead head) {
        this.head = head;
    }

    public List<PaperBody> getBody() {
        return body;
    }

    public void setBody(List<PaperBody> body) {
        this.body = body;
    }

    /**
     * 获取所有试题
     *
     * @return 试题列表
     */
    @JsonIgnore
    public List<Question> getQuestions() {
        return body == null ? List.of() : body.stream().flatMap(i -> i.getQuestions().stream()).toList();
    }

    /**
     * 获取所有试题组
     *
     * @return 试题组列表
     */
    @JsonIgnore
    public List<GroupDto> getProblems() {
        return body == null ? List.of() : body.stream().map(PaperBody::getProblems).flatMap(Collection::stream).toList();
    }
}
