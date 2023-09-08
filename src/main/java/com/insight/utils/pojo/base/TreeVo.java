package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2021/12/10
 * @remark 树形数据VO
 */
public class TreeVo extends TreeBase {

    /**
     * 编码
     */
    private String code;

    /**
     * 节点类型
     */
    private Integer type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
