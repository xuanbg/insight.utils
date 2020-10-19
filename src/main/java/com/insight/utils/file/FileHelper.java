package com.insight.utils.file;

import com.insight.utils.Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author duxl
 * @date 2018年8月24日
 * @remark 文件和io操作工具，作为apache commons 的补充
 */
public final class FileHelper {

    public static boolean testDir(String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            return file.mkdir();
        }

        return true;
    }

    /**
     * 获取
     *
     * @param url url地址
     * @return url
     */
    public static String getUrlContent(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        try {
            URL u = new URL(url);
            return IOUtils.toString(u, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
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
    public static boolean delete(String path) {
        File f = new File(path);
        return FileUtils.deleteQuietly(f);
    }

    /**
     * 比较文件内容
     *
     * @param f1 文件1
     * @param f2 文件2
     * @return 文件是否相同
     */
    public static Boolean compare(String f1, String f2) {
        if (Util.isNotEmpty(f1) && Util.isNotEmpty(f2)) {
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
