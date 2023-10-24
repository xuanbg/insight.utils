package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件DTO
 */
public class FileDto extends File {

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 文件域名
     */
    private String domain;

    /**
     * 文件哈希值
     */
    private String hash;

    /**
     * 文件字节数
     */
    private Integer size;

    /**
     * 文件是否已存在
     */
    private Boolean existed;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getExisted() {
        return existed != null && existed;
    }

    public void setExisted(Boolean existed) {
        this.existed = existed;
    }
}
