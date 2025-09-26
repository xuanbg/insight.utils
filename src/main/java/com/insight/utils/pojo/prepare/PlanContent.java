package com.insight.utils.pojo.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2024/12/5
 * @remark 学案内容实体类
 */
public class PlanContent extends BaseXo {

    /**
     * 课程前提
     */
    private Premise premise;

    /**
     * 课程奖励
     */
    private Award award;

    /**
     * 课程步骤
     */
    private List<PlanModel> models;

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
