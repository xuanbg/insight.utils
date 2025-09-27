package com.insight.utils.pojo.basedata;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2025/1/6
 * @remark 附件实体类
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttachFile extends BaseXo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 扩展名
     */
    @JsonAlias({"ext", "extension"})
    private String ext;

    /**
     * 链接URL
     */
    @JsonAlias({"url", "officeUrl"})
    private String url;

    /**
     * 页面URL
     */
    @JsonAlias({"page", "pdfUrl"})
    private String page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return switch (getExt()) {
            case "png", "jpg", "jpeg", "gif" -> 1;
            case "mp3" -> 2;
            case "mp4" -> 3;
            default -> 4;
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        if (ext != null) {
            return ext.toLowerCase();
        }

        int index = url == null ? -1 : url.lastIndexOf(".");
        return index < 0 ? "." : url.substring(index + 1);
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
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    /**
     * 获取资源类型
     *
     * @return 资源类型
     */
    @JsonIgnore
    public Integer getResourceType() {
        return switch (getExt()) {
            case "docx", "doc", "pdf" -> 2;
            case "pptx" -> 3;
            case "mp4" -> 4;
            default -> 9;
        };
    }

    /**
     * 判断当前附件是否与指定URL一致
     *
     * @param url URL
     * @return 布尔值
     */
    @JsonIgnore
    public Boolean equals(String url) {
        return this.url != null && this.url.equals(url);
    }
}
