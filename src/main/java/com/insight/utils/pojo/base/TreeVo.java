package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2021/12/10
 * @remark 树形数据VO
 */
public class TreeVo extends BaseVo {

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 节点类型
     */
    private Integer type;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
