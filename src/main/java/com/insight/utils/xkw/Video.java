package com.insight.utils.xkw;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2025/11/17
 * @remark
 */
public class Video extends BaseXo {

    /**
     * 视频ID
     */
    private String src;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 封面图片
     */
    private String poster;

    /**
     * 分辨率
     */
    private String resolution;

    /**
     * 文件大小
     */
    @JsonAlias("file_size")
    private Long fileSize;

    /**
     * 时长
     */
    private Long duration;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
