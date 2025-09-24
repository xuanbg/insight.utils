package com.insight.utils.pojo.problem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BaseXo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2021/7/16
 * @remark 试题基类
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProblemBase extends BaseXo {

    /**
     * 试题ID
     */
    private Long id;

    /**
     * 试题组ID
     */
    private Long groupId;

    /**
     * 题型ID
     */
    private Long speciesId;

    /**
     * 题干
     */
    private String question;

    /**
     * 选项
     */
    private Map<String, String> option;

    /**
     * 答案
     */
    private List<String> answer;

    /**
     * 解析
     */
    private String analyze;

    /**
     * 视频地址
     */
    private String videoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, String> getOption() {
        return option;
    }

    public void setOption(Map<String, String> option) {
        if (option == null) {
            return;
        }

        this.option = new HashMap<>();
        for (String key : option.keySet()) {
            String val = option.get(key);
            if (Util.isEmpty(val)) {
                this.option.put(key, null);
            } else {
                this.option.put(key, val);
            }
        }
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public String getAnalyze() {
        return analyze;
    }

    public void setAnalyze(String analyze) {
        this.analyze = analyze;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 判断当前试题是否为指定ID的试题
     *
     * @param id 试题ID
     * @return 是否相等
     */
    @JsonIgnore
    public Boolean equals(Long id) {
        return this.id != null && this.id.equals(id);
    }
}
