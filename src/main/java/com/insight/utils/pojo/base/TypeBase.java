package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2023/9/9
 * @remark 带类型的基础类
 */
public class TypeBase extends TreeBase {

    /**
     * 类型枚举值
     */
    protected Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
