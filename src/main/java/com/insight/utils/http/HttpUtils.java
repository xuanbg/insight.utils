package com.insight.utils.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author luwenbao
 * @date 2018/1/5.
 * @remark
 */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 根据url获取远程文件资源
     * @param url 访问地址
     * @return byte数组
     */
    public static byte[] getByteFromUrl(String url) {
        ByteArrayOutputStream bos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL getUrl;
        byte[] data = null;
        try {
            byte[] buf = new byte[1024];
            getUrl = new URL(url);
            httpUrl = (HttpURLConnection) getUrl.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new ByteArrayOutputStream();
            int size;
            while ((size = bis.read(buf)) != -1) {
                bos.write(buf, 0, size);
            }
            data = bos.toByteArray();
            bos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (httpUrl != null) {
                    httpUrl.disconnect();
                }
            } catch (IOException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
        return data;
    }

}
