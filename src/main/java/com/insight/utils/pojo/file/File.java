package com.insight.utils.pojo.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Util;

import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2023/8/26
 * @remark 文件信息实体类
 */
public class File extends Folder {

    /**
     * 文件名
     */
    private String file;

    /**
     * 扩展名
     */
    private String ext;

    /**
     * 文件哈希值
     */
    private String hash;

    /**
     * 文件域名
     */
    private String domain;

    /**
     * 文件路径
     */
    private String path;

    @Override
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String getName() {
        if (name != null) {
            return name;
        }

        var fullName = getFullName();
        if (Util.isEmpty(fullName)) {
            return null;
        }

        var index = fullName.lastIndexOf(".");
        return index < 0 ? fullName : fullName.substring(0, index);
    }

    public String getExt() {
        if (ext != null) {
            return ext;
        }

        var fullName = getFullName();
        if (Util.isEmpty(fullName)) {
            return null;
        }

        var index = fullName.lastIndexOf(".");
        return index < 0 ? null : fullName.substring(index + 1);
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
        return getPath() == null ? null : "/upload/" + getPath();
    }

    /**
     * 获取文件路径
     *
     * @return 文件路径
     */
    @JsonIgnore
    public String getFullPath() {
        if (Util.isEmpty(file)) {
            return null;
        }

        // 如路径为windows文件路径，则转换成unix文件路径
        if (file.matches(".*:\\\\.*")) {
            var index = path.indexOf(":");
            var path = file.substring(index + 1);
            return path.replaceAll("\\\\", "/").trim();
        }

        return file.trim();
    }

    /**
     * 判断两个文件是否相等
     *
     * @param file 文件
     * @return 是否相等
     */
    public Boolean equals(File file) {
        return Objects.equals(hash, file.hash) && Objects.equals(getName(), file.getName()) && Objects.equals(getExt(), file.getExt());
    }

    /**
     * 获取完整的文件名
     *
     * @return 完整的文件名，如果路径为空则返回null
     */
    private String getFullName() {
        var fullPath = getFullPath();
        if (Util.isEmpty(fullPath)) {
            return null;
        }

        var index = fullPath.lastIndexOf("/");
        return index < 0 ? fullPath : fullPath.substring(index + 1);
    }
}
