package com.insight.util.file;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author duxl
 * @date 2018年8月24日
 * @remark 文件和io操作工具，作为apache commons 的补充
 */
public final class FileHelper {
    /**
     * 获取
     *
     * @param url url地址
     * @return
     */
    public static String getUrlContent(String url) {
        if (StringUtils.isNotBlank(url)) {
            try {
                URL u = new URL(url);
                return IOUtils.toString(u, "UTF-8");
            } catch (IOException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * 拷贝文件
     *
     * @param source 原文件
     * @param dest   目标文件
     */
    public static void copy(String source, String dest) {
        try {
            File srcFile = new File(source);
            File destFile = new File(dest);
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件或目录下的文件
     *
     * @param path 要删除的文件path
     */
    public static void delete(String path) {
        File f = new File(path);
        FileUtils.deleteQuietly(f);
    }

    /**
     * 比较文件内容
     *
     * @param f1 文件1
     * @param f2 文件2
     * @return
     */
    public static Boolean compare(String f1, String f2) {
        if (StringUtils.isNotBlank(f1) && StringUtils.isNotBlank(f2)) {
            File fa = new File(f1);
            File fb = new File(f2);
            try {
                return FileUtils.contentEquals(fa, fb);
            } catch (IOException e) {
                return false;
            }
        }

        return false;
    }

    /**
     * 移动文件到指定目录
     *
     * @param source 原文件
     * @param dir    目标目录
     */
    public static void move(String source, String dir) {
        try {
            File srcFile = new File(source);
            File destDir = new File(dir);
            FileUtils.moveToDirectory(srcFile, destDir, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
