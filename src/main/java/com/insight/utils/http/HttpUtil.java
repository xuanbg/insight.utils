package com.insight.utils.http;

import com.insight.utils.Json;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author 宣炳刚
 * @date 2019/9/26
 * @remark HttpClient工具类
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final CloseableHttpClient HTTPCLIENT = HttpClients.createDefault();

    /**
     * GET方法
     *
     * @param url  URL
     * @param type 返回数据类型
     * @param <T>  泛型类型
     * @return 数据实体
     */
    public static <T> T get(String url, Class<T> type) {
        return get(url, "UTF-8", type);
    }

    /**
     * GET方法
     *
     * @param url     URL
     * @param headers 请求头
     * @param type    返回数据类型
     * @param <T>     泛型类型
     * @return 数据实体
     */
    public static <T> T get(String url, Map<String, String> headers, Class<T> type) {
        return get(url, headers, "UTF-8", type);
    }

    /**
     * GET方法
     *
     * @param url      URL
     * @param encoding 返回数据编码
     * @param type     返回数据类型
     * @param <T>      泛型类型
     * @return 数据实体
     */
    public static <T> T get(String url, String encoding, Class<T> type) {
        return get(url, null, encoding, type);
    }

    /**
     * GET方法
     *
     * @param url      URL
     * @param headers  请求头
     * @param encoding 返回数据编码
     * @param type     返回数据类型
     * @param <T>      泛型类型
     * @return 数据实体
     */
    public static <T> T get(String url, Map<String, String> headers, String encoding, Class<T> type) {
        HttpGet request = new HttpGet(url);
        String result = execute(request, headers, encoding);

        return Json.toBean(result, type);
    }

    /**
     * POST方法
     *
     * @param url  URL
     * @param type 返回数据类型
     * @param <T>  泛型类型
     * @return 数据实体
     */
    public static <T> T post(String url, Class<T> type) {
        return post(url, null, type);
    }

    /**
     * POST方法
     *
     * @param url  URL
     * @param body 请求体
     * @param type 返回数据类型
     * @param <T>  泛型类型
     * @return 数据实体
     */
    public static <T> T post(String url, Object body, Class<T> type) {
        return post(url, body, "UTF-8", type);
    }

    /**
     * POST方法
     *
     * @param url     URL
     * @param body    请求体
     * @param headers 请求头
     * @param type    返回数据类型
     * @param <T>     泛型类型
     * @return 数据实体
     */
    public static <T> T post(String url, Object body, Map<String, String> headers, Class<T> type) {
        return post(url, body, headers, "UTF-8", type);
    }

    /**
     * POST方法
     *
     * @param url      URL
     * @param body     请求体
     * @param encoding 返回数据编码
     * @param type     返回数据类型
     * @param <T>      泛型类型
     * @return 数据实体
     */
    public static <T> T post(String url, Object body, String encoding, Class<T> type) {
        return post(url, body, null, encoding, type);
    }

    /**
     * POST方法
     *
     * @param url      URL
     * @param body     请求体
     * @param headers  请求头
     * @param encoding 返回数据编码
     * @param type     返回数据类型
     * @param <T>      泛型类型
     * @return 数据实体
     */
    public static <T> T post(String url, Object body, Map<String, String> headers, String encoding, Class<T> type) {
        HttpPost request = new HttpPost(url);
        if (body != null) {
            HttpEntity entity = new StringEntity(Json.toJson(body), ContentType.APPLICATION_JSON);
            request.setEntity(entity);
        }

        String result = execute(request, headers, encoding);
        return Json.toBean(result, type);
    }

    /**
     * PUT方法
     *
     * @param url  URL
     * @param type 返回数据类型
     * @param <T>  泛型类型
     * @return 数据实体
     */
    public static <T> T put(String url, Class<T> type) {
        return put(url, null, type);
    }

    /**
     * PUT方法
     *
     * @param url  URL
     * @param body 请求体
     * @param type 返回数据类型
     * @param <T>  泛型类型
     * @return 数据实体
     */
    public static <T> T put(String url, Object body, Class<T> type) {
        return put(url, body, "UTF-8", type);
    }

    /**
     * PUT方法
     *
     * @param url     URL
     * @param body    请求体
     * @param headers 请求头
     * @param type    返回数据类型
     * @param <T>     泛型类型
     * @return 数据实体
     */
    public static <T> T put(String url, Object body, Map<String, String> headers, Class<T> type) {
        return put(url, body, headers, "UTF-8", type);
    }

    /**
     * PUT方法
     *
     * @param url      URL
     * @param body     请求体
     * @param encoding 返回数据编码
     * @param type     返回数据类型
     * @param <T>      泛型类型
     * @return 数据实体
     */
    public static <T> T put(String url, Object body, String encoding, Class<T> type) {
        return put(url, body, null, encoding, type);
    }

    /**
     * PUT方法
     *
     * @param url      URL
     * @param body     请求体
     * @param headers  请求头
     * @param encoding 返回数据编码
     * @param type     返回数据类型
     * @param <T>      泛型类型
     * @return 数据实体
     */
    public static <T> T put(String url, Object body, Map<String, String> headers, String encoding, Class<T> type) {
        HttpPut request = new HttpPut(url);
        if (body != null) {
            HttpEntity entity = new StringEntity(Json.toJson(body), ContentType.APPLICATION_JSON);
            request.setEntity(entity);
        }

        String result = execute(request, headers, encoding);
        return Json.toBean(result, type);
    }

    /**
     * DELETE方法
     *
     * @param url  URL
     * @param type 返回数据类型
     * @param <T>  泛型类型
     * @return 数据实体
     */
    public static <T> T delete(String url, Class<T> type) {
        return delete(url, null, type);
    }

    /**
     * DELETE方法
     *
     * @param url  URL
     * @param body 请求体
     * @param type 返回数据类型
     * @param <T>  泛型类型
     * @return 数据实体
     */
    public static <T> T delete(String url, Object body, Class<T> type) {
        return delete(url, body, "UTF-8", type);
    }

    /**
     * DELETE方法
     *
     * @param url     URL
     * @param body    请求体
     * @param headers 请求头
     * @param type    返回数据类型
     * @param <T>     泛型类型
     * @return 数据实体
     */
    public static <T> T delete(String url, Object body, Map<String, String> headers, Class<T> type) {
        return delete(url, body, headers, "UTF-8", type);
    }

    /**
     * DELETE方法
     *
     * @param url      URL
     * @param body     请求体
     * @param encoding 返回数据编码
     * @param type     返回数据类型
     * @param <T>      泛型类型
     * @return 数据实体
     */
    public static <T> T delete(String url, Object body, String encoding, Class<T> type) {
        return delete(url, body, null, encoding, type);
    }

    /**
     * DELETE方法
     *
     * @param url      URL
     * @param body     请求体
     * @param headers  请求头
     * @param encoding 返回数据编码
     * @param type     返回数据类型
     * @param <T>      泛型类型
     * @return 数据实体
     */
    public static <T> T delete(String url, Object body, Map<String, String> headers, String encoding, Class<T> type) {
        HttpDeleteWithBody request = new HttpDeleteWithBody(url);
        if (body != null) {
            HttpEntity entity = new StringEntity(Json.toJson(body), ContentType.APPLICATION_JSON);
            request.setEntity(entity);
        }

        String result = execute(request, headers, encoding);
        return Json.toBean(result, type);
    }

    /**
     * 发送HTTP请求
     *
     * @param request  HttpUriRequest
     * @param encoding 返回数据编码
     * @return 返回数据
     */
    private static String execute(HttpUriRequest request, Map<String, String> headers, String encoding) {
        if (headers != null) {
            headers.forEach(request::setHeader);
        }

        try {
            CloseableHttpResponse response = HTTPCLIENT.execute(request);
            if (response == null) {
                return null;
            }

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, encoding);
            response.close();

            return result;
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }
}
