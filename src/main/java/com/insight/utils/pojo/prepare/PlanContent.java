package com.insight.utils.pojo.prepare;

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
}
