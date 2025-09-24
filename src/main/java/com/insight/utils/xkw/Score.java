package com.insight.utils.xkw;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2025/9/1
 * @remark
 */
public class Score extends BaseXo {

    /**
     * 小题序号
     */
    private Integer index;

    /**
     * 小题分数
     */
    private Integer score;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
