package com.insight.utils.pojo.basedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Json;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.base.BusinessException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2024/11/9
 * @remark 教学资源实体类
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Education extends BaseXo {

    /**
     * 资源ID
     */
    protected Long id;

    /**
     * 学科ID
     */
    protected Long subjectId;

    /**
     * 资源类型: 0.教案, 1.思维导图, 2.导学案, 3.课件, 4.微课, 5.堂练, 6.堂测, 7.作业, 8.试卷, 9.素材
     */
    protected Integer type;

    /**
     * 共享级别: 0.私有, 1.本校, 2.集团
     */
    protected Integer level;

    /**
     * 名称
     */
    protected String name;

    /**
     * 内容
     */
    protected Content content;

    /**
     * 内容哈希值
     */
    protected String hash;

    /**
     * 简介
     */
    protected String remark;

    /**
     * 慧学分
     */
    protected BigDecimal points;

    /**
     * 创建人
     */
    protected String creator;

    /**
     * 创建人ID
     */
    protected Long creatorId;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getHash() {
        if (hash != null) {
            return hash;
        }

        if (content == null) {
            throw new BusinessException("资源内容不存在");
        }

        if (isFile()) {
            var data = Util.isEmpty(content.getFiles()) ? content.getUrl() : content.getUrl() + Json.toJson(content.getFiles());
            return Util.md5(data);
        } else {
            return Util.md5(Json.toJson(content));
        }
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getPoints() {
        return points == null ? BigDecimal.ZERO : points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 是否是试卷
     *
     * @return 是否是试卷
     */
    @JsonIgnore
    public Boolean isPaper() {
        return List.of(5, 6, 7).contains(type);
    }

    /**
     * 是否是文件
     *
     * @return 是否是文件
     */
    @JsonIgnore
    public Boolean isFile() {
        return switch (type) {
            case 0, 2 -> content != null && content.getType() == 3;
            case 3, 4, 9 -> true;
            default -> false;
        };
    }

    /**
     * 设置资源URL
     *
     * @param url 资源URL
     */
    @JsonIgnore
    public void setUrl(String url) {
        content.setUrl(url);
    }

    /**
     * 设置资源预览页面
     *
     * @param page 页码
     */
    @JsonIgnore
    public void setPage(String page) {
        content.setPage(page);
    }

    /**
     * 添加附件文件
     *
     * @param file 附件文件
     */
    @JsonIgnore
    public void addFile(AttachFile file) {
        content.addFile(file);
    }
}
