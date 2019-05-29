package com.insight.util.pojo;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * @author 宣炳刚
 * @date 2017/10/6
 * @remark
 */
public class BodyReaderRequestWrapper extends HttpServletRequestWrapper {
    private ByteArrayOutputStream cachedBytes;

    /**
     * 构造方法
     *
     * @param request HttpServletRequest
     */
    public BodyReaderRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 缓存输入流
     *
     * @throws IOException IOException
     */
    private void cacheInputStream() throws IOException {
        cachedBytes = new ByteArrayOutputStream();
        IOUtils.copy(super.getInputStream(), cachedBytes);
    }

    /**
     * 获取输入流
     *
     * @return ServletInputStream
     * @throws IOException IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (cachedBytes == null) {
            cacheInputStream();
        }

        return new CachedServletInputStream();
    }

    /**
     * 获取流读取器
     *
     * @return BufferedReader
     * @throws IOException IOException
     */
    @Override
    public BufferedReader getReader() throws IOException {
        InputStreamReader streamReader = new InputStreamReader(getInputStream(), Charset.defaultCharset());
        return new BufferedReader(streamReader);
    }

    /**
     * CachedServletInputStream内部类
     */
    public class CachedServletInputStream extends ServletInputStream {
        private ByteArrayInputStream input;

        public CachedServletInputStream() {
            input = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
        }

        @Override
        public int read() {
            return input.read();
        }
    }
}
