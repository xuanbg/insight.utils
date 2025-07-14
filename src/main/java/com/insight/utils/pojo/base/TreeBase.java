package com.insight.utils.pojo.base;


import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2021/12/10
 * @remark 树形数据VO
 */
public class TreeBase extends DataBase {

    /**
     * 父级ID
     */
    protected Long parentId;

    /**
     * 子节点数量
     */
    protected Integer count;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 判断当前对象与给定的parentId是否相等。
     *
     * @param parentId 父id
     * @return 如果当前对象的parentId与给定的parentId相等，则返回true；否则返回false。
     */
    public Boolean parentMatch(Long parentId) {
        return Objects.equals(this.parentId, parentId);
    }

    /**
     * 判断当前对象与给定的parentId和name是否相等。
     *
     * @param parentId 父id
     * @param name 名称
     * @return 如果当前对象的parentId和name与给定的parentId和name相等，则返回true；否则返回false。
     */
    public Boolean equals(Long parentId, String name) {
        return Objects.equals(this.parentId, parentId) && Objects.equals(super.name, name);
    }
}
