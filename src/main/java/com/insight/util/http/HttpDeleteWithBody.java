package com.insight.util.http;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * @author 宣炳刚
 * @date 2019/9/29
 * @remark
 */
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    private static final String METHOD_NAME = "DELETE";

    /**
     * 获取请求方法
     *
     * @return 请求方法
     */
    @Override
    public String getMethod() {
        return METHOD_NAME;
    }

    /**
     * 构造方法
     */
    public HttpDeleteWithBody() {
        super();
    }

    /**
     * 构造方法
     *
     * @param url URL
     */
    public HttpDeleteWithBody(final String url) {
        super();
        setURI(URI.create(url));
    }

    /**
     * 构造方法
     *
     * @param uri URI
     */
    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }
}
