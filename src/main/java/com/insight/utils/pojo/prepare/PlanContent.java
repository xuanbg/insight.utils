package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insight.utils.pojo.base.BaseXo;
import com.insight.utils.pojo.basedata.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2024/12/5
 * @remark 学案内容实体类
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlanContent extends BaseXo {

    /**
     * 资源ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private Content content;

    /**
     * 课程前提
     */
    private Premise premise = new Premise();

    /**
     * 课程奖励
     */
    private Award award = new Award();

    /**
     * 课程步骤
     */
    private List<PlanModel> models;

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

    public Premise getPremise() {
        return premise;
    }

    public void setPremise(Premise premise) {
        this.premise = premise;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public List<PlanModel> getModels() {
        return models == null ? new ArrayList<>() : models;
    }

    public void setModels(List<PlanModel> models) {
        this.models = models;
    }

    /**
     * 添加模块
     *
     * @param model 学程模块
     */
    @JsonIgnore
    public void addModel(PlanModel model) {
        if (models == null) {
            models = new ArrayList<>();
        }

        models.add(model);
    }

    /**
     * 是否有目标模块
     *
     * @return 是否有目标模块
     */
    @JsonIgnore
    public Boolean hasObject() {
        return models.stream().anyMatch(m -> m.getType() == 1);
    }

    /**
     * 获取目标模块
     *
     * @return 目标模块
     */
    @JsonIgnore
    public Resource getObject() {
        return models.stream().filter(m -> m.getType() == 1).findFirst().map(PlanModel::getData).orElse(null);
    }

    /**
     * 获取所有模型类型
     *
     * @return 所有模型类型
     */
    @JsonIgnore
    public List<Integer> getModelTypes() {
        return models.stream().map(PlanModel::getType).toList();
    }

    /**
     * 获取所有资源ID
     *
     * @return 所有资源ID
     */
    @JsonIgnore
    public List<Long> getResourceIds() {
        return models.stream().filter(PlanModel::hasResource)
                .map(model -> model.getResource().getId()).toList();
    }
}
