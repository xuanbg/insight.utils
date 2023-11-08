package com.insight.utils.pojo.file;

/**
 * @author 宣炳刚
 * @date 2023/11/8
 * @remark 文件信息VO
 */
public class FileVo extends File {

    /**
     * 文件上传令牌
     */
    private String token;

    /**
     * 七牛文件空间bucket
     */
    private String bucket;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
