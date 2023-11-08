package com.insight.utils.pojo.file;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2023/8/26
 * @remark 文件夹信息实体类
 */
public class Folder extends BaseXo {

    /**
     * 文件ID
     */
    protected Long id;

    /**
     * 父级ID
     */
    protected Long parentId;

    /**
     * 类型: 0.文件夹, 1.图片, 2.音频, 3.视频, 4.文档, 5.压缩包, 6.其他
     */
    protected Integer type;

    /**
     * 名称
     */
    protected String name;

    /**
     * 拥有人ID
     */
    protected Long ownerId;

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

    public Integer getType() {
        return type == null ? 0 : type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
