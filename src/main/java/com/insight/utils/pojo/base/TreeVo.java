package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2021/12/10
 * @remark 树形数据VO
 */
public class TreeVo extends BaseXo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
