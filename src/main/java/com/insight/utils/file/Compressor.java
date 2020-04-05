package com.insight.utils.file;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author duxl
 * @date 2017年8月24日
 * @remark 压缩解压缩工具，支持zip格式
 */
public final class Compressor {

    private Compressor() {
    }

    /**
     * zip压缩文件
     *
     * @param dir     要压缩的目录
     * @param zipPath zip文件生成路径
     */
    public static void zip(String dir, String zipPath) {
        List<String> paths = getFiles(dir);
        compressFilesZip(paths.toArray(new String[paths.size()]), zipPath, dir);
    }

    /**
     * 递归获取目录所有文件
     *
     * @param dir 目录
     * @return 文件列表
     */
    private static List<String> getFiles(String dir) {
        List<String> lstFiles = new ArrayList<>();
        File file = new File(dir);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                lstFiles.add(f.getAbsolutePath());
                lstFiles.addAll(getFiles(f.getAbsolutePath()));
            } else {
                String str = f.getAbsolutePath();
                lstFiles.add(str);
            }
        }

        return lstFiles;
    }

    /**
     * 文件名处理
     *
     * @param dir  目录
     * @param path
     * @return
     */
    private static String getFilePathName(String dir, String path) {
        String p = path.replace(dir + File.separator, "");

        return p.replace("\\", "/");
    }

    /**
     * 把文件压缩成zip格式
     *
     * @param files       需要压缩的文件
     * @param zipFilePath 压缩后的zip文件路径
     */
    private static void compressFilesZip(String[] files, String zipFilePath, String dir) {
        if (files == null || files.length <= 0) {
            return;
        }

        ZipArchiveOutputStream zaos = null;
        try {
            File zipFile = new File(zipFilePath);
            zaos = new ZipArchiveOutputStream(zipFile);
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            for (String strfile : files) {
                File file = new File(strfile);
                if (file == null) {
                    break;
                }
                String name = getFilePathName(dir, strfile);
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, name);
                zaos.putArchiveEntry(zipArchiveEntry);
                if (file.isDirectory()) {
                    continue;
                }
                InputStream is = null;
                try {
                    is = new BufferedInputStream(new FileInputStream(file));
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = is.read(buffer)) != -1) {
                        zaos.write(buffer, 0, len);
                    }
                    zaos.closeArchiveEntry();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
            zaos.finish();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (zaos != null) {
                    zaos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 把zip文件解压到指定的文件夹
     *
     * @param zipFilePath zip文件路径
     * @param saveFileDir 解压后的文件存放路径
     */
    public static void unzip(String zipFilePath, String saveFileDir) {
        if (!saveFileDir.endsWith("\\") && !saveFileDir.endsWith("/")) {
            saveFileDir += File.separator;
        }

        File dir = new File(saveFileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(zipFilePath);
        if (!file.exists()) {
            return;
        }

        InputStream is = null;
        ZipArchiveInputStream zais = null;
        try {
            is = new FileInputStream(file);
            zais = new ZipArchiveInputStream(is);
            ArchiveEntry archiveEntry = null;
            while ((archiveEntry = zais.getNextEntry()) != null) {
                String entryFileName = archiveEntry.getName();
                String entryFilePath = saveFileDir + entryFileName;
                OutputStream os = null;
                try {
                    File entryFile = new File(entryFilePath);
                    if (entryFileName.endsWith("/")) {
                        entryFile.mkdirs();
                    } else {
                        os = new BufferedOutputStream(new FileOutputStream(entryFile));
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = zais.read(buffer)) != -1) {
                            os.write(buffer, 0, len);
                        }
                    }
                } catch (IOException e) {
                    throw new IOException(e);
                } finally {
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (zais != null) {
                    zais.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
