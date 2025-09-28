package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.basedata.AttachFile;
import com.insight.utils.pojo.basedata.Content;
import com.insight.utils.pojo.basedata.Education;
import com.insight.utils.pojo.paper.ProblemGroup;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2024/12/15
 * @remark 备课资源
 */
public class Resource extends Education {

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
        return content == null ? null : content.getFiles();
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

        files.forEach(this::addFile);
    }
}
