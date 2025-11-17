package com.insight.utils.xkw;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2025/11/17
 * @remark
 */
public class VideoBody extends BaseXo {

    /**
     * 试题ID
     */
    @JsonAlias("question_id")
    private String questionId;

    /**
     * 视频信息
     */
    private Video videos;

    /**
     * 试题解析
     */
    private String explanation;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Video getVideos() {
        return videos;
    }

    public void setVideos(Video videos) {
        this.videos = videos;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
