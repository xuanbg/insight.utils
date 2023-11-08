package com.insight.utils.pojo.file;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件信息DTO
 */
public class FileDto extends File {

    /**
     * 文件哈希值
     */
    private String hash;

    /**
     * 文件字节数
     */
    private Long size;

    /**
     * 文件是否已存在
     */
    private Boolean existed;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Boolean getExisted() {
        return existed != null && existed;
    }

    public void setExisted(Boolean existed) {
        this.existed = existed;
    }
}
