package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Json;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.paper.AttachFile;
import com.insight.utils.pojo.paper.ProblemGroup;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2024/12/15
 * @remark 备课资源
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Resource extends BaseXo {

    /**
     * 资源ID
     */
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源内容
     */
    private Content content;

    /**
     * 试题集合
     */
    private List<ProblemGroup> problems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<ProblemGroup> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemGroup> problems) {
        this.problems = problems;
    }

    @JsonIgnore
    public List<AttachFile> getFiles() {
        return content == null ? null : Json.toList(content.getFiles(), AttachFile.class);
    }
}
