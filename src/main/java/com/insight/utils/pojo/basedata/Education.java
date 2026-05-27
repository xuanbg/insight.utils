package com.insight.utils.pojo.basedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Json;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
     * 得分
     */
    private Integer score;

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
            return Util.md5("{}");
        }

        if (isFile()) {
            var data = Util.isEmpty(content.getFiles()) ? content.getUrl() : content.getUrl() + Json.toJson(content.getFiles());
            return Util.md5(data);
        } else {
            var data = content.toTreeMap();
            return Util.md5(Json.toJson(data));
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
     * 内容类型: 1.思维导图, 2.富文本, 3.PDF, 4.课件, 5.试卷, 6.答题卡, 7.图片, 8.音频, 9.视频
     */
    public Integer getContentType() {
        return content == null || type == null ? 0 : switch (type) {
            case 0, 2 -> switch (content.getType()) {
                case 0 -> 2;
                case 3 -> 3;
                default -> 0;
            };
            case 1 -> 1;
            case 3 -> 4;
            case 4 -> 7;
            case 5, 6, 7 -> {
                // 文本试卷
                if (Util.isNotEmpty(content.getHtml())) {
                    yield 2;
                }

                // 判断是试卷还是答题卡
                var groups = content.getGroups();
                if (Util.isEmpty(groups)) {
                    yield 0;
                }

                var group = groups.stream().filter(i -> Util.isNotEmpty(i.getProblems())).findFirst().orElse(null);
                if (group == null) {
                    yield 0;
                }

                var problems = group.getProblemList();
                if (Util.isEmpty(problems)) {
                    yield 0;
                }

                var problem = problems.get(0);
                yield Util.isEmpty(problem.getAnswer()) ? 5 : 6;
            }
            case 9 -> content.getExt() == null ? 0 : switch (content.getExt()) {
                case "pdf", "doc", "docx", "xls", "xlsx" -> 3;
                case "ppt", "pptx" -> 4;
                case "png", "jpg", "jpeg", "gif" -> 7;
                case "mp3" -> 8;
                case "mp4" -> 9;
                default -> 0;
            };
            default -> 0;
        };
    }

    /**
     * 是否是试卷
     *
     * @return 是否是试卷
     */
    @JsonIgnore
    public Boolean isPaper() {
        return type != null
               && List.of(5, 6, 7).contains(type)
               && content.getType() != null
               && content.getType() == 1
               && content != null
               && Util.isNotEmpty(content.getGroupIds());
    }

    /**
     * 是否是文件
     *
     * @return 是否是文件
     */
    @JsonIgnore
    public Boolean isFile() {
        return type != null && switch (type) {
            case 0, 2 -> content != null && content.getType() == 3;
            case 3, 4, 9 -> true;
            default -> false;
        };
    }

    /**
     * 当前用户是否可编辑
     *
     * @param data 当前数据
     * @return 是否可编辑
     */
    @JsonIgnore
    public Boolean canEdit(Education data) {
        return data != null && Objects.equals(creatorId, data.getCreatorId())
               && (level == 0 || Objects.equals(hash, data.getHash()));
    }

    /**
     * 当前数据内容是否一致
     *
     * @param data 当前数据
     * @return 是否一致
     */
    @JsonIgnore
    public Boolean contentEquals(Education data) {
        return data != null && content != null
               && (Objects.equals(getHash(), data.getHash()) ||
                   data.getContent() != null && "pptx".equals(data.getContent().getExt())
                   && Objects.equals(content.getUrl(), data.getContent().getUrl()));
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
