package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Json;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.basedata.Content;
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
     * 资源类型
     */
    private Integer type;

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

    /**
     * 默认构造函数
     */
    public Resource() {
    }

    /**
     * 构造函数
     *
     * @param file 附件
     */
    public Resource(AttachFile file) {
        this.id = file.getId();
        this.type = file.getResourceType();
        this.name = file.getName();
        this.content = file.convert(Content.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    /**
     * 获取所有附件
     *
     * @return 附件集合
     */
    @JsonIgnore
    public List<AttachFile> getFiles() {
        return content == null ? null : Json.toList(content.getFiles(), AttachFile.class);
    }

    /**
     * 添加附件
     *
     * @param files 附件集合
     */
    @JsonIgnore
    public void setFiles(List<AttachFile> files) {
        if (content == null) {
            return;
        }

        content.setFiles(files);
    }

    /**
     * 添加附件
     *
     * @param file 附件
     */
    @JsonIgnore
    public void addFile(AttachFile file) {
        if (content == null) {
            return;
        }

        var files = content.getFiles();
        if (files.stream().noneMatch(i -> i.getId().equals(file.getId()))) {
            files.add(file);
        }
    }

    @JsonIgnore
    public String getHash() {
        if (type != null && List.of(0, 2, 3, 4, 9).contains(type)) {
            if (Util.isNotEmpty(content.getUrl())) {
                return Util.md5(content.getUrl());
            }
        }

        return Util.md5(Json.toJson(content));
    }
}
