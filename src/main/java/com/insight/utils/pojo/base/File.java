package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2023/8/26
 * @remark
 */
public class File extends BaseXo {

    /**
     * 文件ID
     */
    private Long id;

    /**
     * 类型: 0.文件夹, 1.图片, 2.音频, 3.视频, 4.文档, 5.压缩包, 6.其他
     */
    private Integer type;

    /**
     * 文件名
     */
    protected String file;

    /**
     * 名称
     */
    private String name;

    /**
     * 扩展名
     */
    private String ext;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 拥有人ID
     */
    protected Long ownerId;

    /**
     * 文件上传令牌
     */
    private String token;

    /**
     * 七牛文件空间bucket
     */
    private String bucket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        if (type != null) {
            return type;
        }

        if (getExt() == null) {
            return 0;
        }

        return switch (getExt().toLowerCase()) {
            case "bmp", "jpg", "jpeg", "gif", "png", "svg", "tif" -> 1;
            case "alac", "ape", "flac", "mp3", "wav" -> 2;
            case "avi", "flv", "mp4", "mpg", "mpeg", "mov", "swf", "wmv" -> 3;
            case "doc", "docx", "md", "pdf", "ppt", "pptx", "txt", "xls", "xlsx", "xw" -> 4;
            case "7z", "rar", "zip" -> 5;
            default -> 6;
        };
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        if (name != null) {
            return name;
        }

        var fullName = getFullName();
        if (fullName == null) {
            return null;
        }

        var array = fullName.split("\\.");
        return array[0];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        if (ext != null) {
            return ext;
        }

        var fullName = getFullName();
        if (fullName == null) {
            return null;
        }

        var array = fullName.split("\\.");
        return array.length > 1 ? array[1] : null;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPath() {
        if ((getType() == 0)) {
            return null;
        }

        if (path != null) {
            return path;
        }

        return ownerId + switch (getType()) {
            case 1 -> "/picture/";
            case 2 -> "/audio/";
            case 3 -> "/video/";
            case 4 -> "/document/";
            default -> "/other/";
        } + getFullName();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return "/upload/" + getPath();
    }

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

    public String getFullPath() {
        if (file == null) {
            return null;
        }

        var array = file.split(":");
        var path = array.length > 1 ? array[1] : array[0];

        return path.replaceAll("\\\\", "/").trim();
    }

    private String getFullName() {
        var fullPath = getFullPath();
        if (fullPath == null) {
            return null;
        }

        var split = fullPath.lastIndexOf("/");
        return split < 0 ? fullPath : fullPath.substring(split);
    }
}
