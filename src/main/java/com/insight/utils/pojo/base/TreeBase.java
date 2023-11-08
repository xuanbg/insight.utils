package com.insight.utils.pojo.base;


import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2021/12/10
 * @remark 树形数据VO
 */
public class TreeBase extends BaseXo {

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

    /**
     * 判断当前对象与给定的parentId和name是否相等。
     *
     * @param parentId 父id
     * @param name 名称
     * @return 如果当前对象的parentId和name与给定的parentId和name相等，则返回true；否则返回false。
     */
    public Boolean equals(Long parentId, String name) {
        return Objects.equals(this.parentId, parentId) && Objects.equals(this.name, name);
    }
}
