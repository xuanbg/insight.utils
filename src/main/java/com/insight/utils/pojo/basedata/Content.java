package com.insight.utils.pojo.basedata;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.base.DataBase;
import com.insight.utils.pojo.paper.PaperGroup;
import com.insight.utils.pojo.paper.ProblemCore;
import com.insight.utils.pojo.paper.ProblemGroup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2025/9/15
 * @remark
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Content extends BaseXo {

    /**
     * 内容类型
     */
    private Integer type;

    /**
     * 内容扩展名
     */
    @JsonAlias({"ext", "extension"})
    private String ext;

    /**
     * 内容地址
     */
    @JsonAlias({"url", "officeUrl"})
    private String url;

    /**
     * 内容页
     */
    @JsonAlias({"page", "pdfUrl"})
    private String page;

    /**
     * 附件
     */
    @JsonAlias({"attachFiles", "files"})
    private List<AttachFile> files;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容HTML
     */
    private String html;

    /**
     * 习题分组
     */
    private List<PaperGroup> groups;

    /**
     * 微课问题
     */
    private List<Object> questions;

    /**
     * 设置
     */
    private Object setting;

    /**
     * 教学目标集合
     */
    private List<DataBase> objects;

    /**
     * 元数据
     */
    private Object meta;

    /**
     * 格式
     */
    private String format;

    /**
     * 数据
     */
    private List<Object> data;

    /**
     * 原始数据
     */
    private List<Object> source;

    public Integer getType() {
        if (type == null) {
            if (Util.isNotEmpty(html)) {
                return 0;
            }

            if (Util.isNotEmpty(groups)) {
                var list = groups.get(0).getProblemList();
                return Util.isEmpty(list) || Util.isEmpty(list.get(0).getAnswer()) ? 1 : 2;
            }

            if (Util.isNotEmpty(getExt()) && "pdf|doc|docx".contains(getExt())) {
                return 3;
            }
        }

        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExt() {
        if (ext != null) {
            return ext.toLowerCase();
        }

        var index = url == null ? -1 : url.lastIndexOf(".");
        return index < 0 ? null : url.substring(index + 1);
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPage() {
        return page == null && getType() != null && getType() == 3 ? url : page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<AttachFile> getFiles() {
        return files == null ? new ArrayList<>() : files;
    }

    public void setFiles(List<AttachFile> files) {
        this.files = files;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
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

    public Object getSetting() {
        return setting;
    }

    public void setSetting(Object setting) {
        this.setting = setting;
    }

    public List<DataBase> getObjects() {
        return objects;
    }

    public void setObjects(List<DataBase> objects) {
        this.objects = objects;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public List<Object> getSource() {
        return source;
    }

    public void setSource(List<Object> source) {
        this.source = source;
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
     * 获取指定习题
     *
     * @param id 习题ID
     * @return 习题
     */
    @JsonIgnore
    public ProblemCore getProblem(Long id) {
        return getProblems().stream().filter(i -> i.equals(id)).findFirst().orElse(null);
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

    /**
     * 添加附件
     *
     * @param file 附件
     */
    @JsonIgnore
    public void addFile(AttachFile file) {
        if (files == null) {
            files = new ArrayList<>();
        }

        if (files.stream().noneMatch(i -> i.getId().equals(file.getId()))) {
            files.add(file);
        }
    }

    /**
     * 获取真实页面
     *
     * @return 真实页面
     */
    @JsonIgnore
    public String getRealPage() {
        return page;
    }
}
