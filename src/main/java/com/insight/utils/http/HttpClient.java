package com.insight.utils.http;

import com.insight.utils.Json;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author luwenbao
 * @date 2018/1/11.
 * @remark
 */
public final class HttpClient {

    /**
     * 每个路由最大连接数
     */
    public static final int MAX_CONN_PER_ROUTE = 500;

    /**
     * 整个连接池最大连接数
     */
    public static final int MAX_CONN_TOTAL = 5000;

    /**
     * 1秒钟 毫秒数
     */
    public static final int SECONDS = 1000;

    /**
     * 数据传输过程中数据包之间间隔的最大时间
     */
    public static final int SOCKET_TIMEOUT = 600 * SECONDS;

    /**
     * 三次握手完成时间
     */
    public static final int CONNECT_TIMEOUT = 300 * SECONDS;

    /**
     * 从连接池获取连接的超时时间
     */
    public static final int CONNECTION_REQUEST_TIMEOUT = 5 * SECONDS;

    private static org.apache.http.client.HttpClient apiHttpClient = null;

    /**
     * 创建 httpclient
     *
     * @return 请求结果
     */
    public static org.apache.http.client.HttpClient getHttpClient() {
        if (apiHttpClient == null) {
            apiHttpClient = createDefaultHttpClient(createDefaultHttpClientConfig());
        }

        return apiHttpClient;
    }

    /**
     * 创建默认的请求配置
     *
     * @return 请求配置
     */
    public static RequestConfig createDefaultHttpClientConfig() {
        return RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setRedirectsEnabled(false).build();
    }

    /**
     * 创建默认的httpclient
     *
     * @param config 请求配置
     * @return 请求客户端
     */
    public static CloseableHttpClient createDefaultHttpClient(RequestConfig config) {
        return HttpClients.custom().setDefaultRequestConfig(config).setMaxConnPerRoute(MAX_CONN_PER_ROUTE).setMaxConnTotal(MAX_CONN_TOTAL).build();
    }

    /**
     * http execute 执行http请求
     *
     * @param request      HttpUriRequest
     * @param resultEncode 返回编码
     * @return 请求结果
     */
    private static String execute(HttpUriRequest request, String resultEncode) {
        HttpResponse response = null;
        String result;
        try {
            response = getHttpClient().execute(request);
            var resEntity = response.getEntity();
            result = EntityUtils.toString(resEntity, resultEncode);

            return result;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

    /**
     * http get请求
     *
     * @param requestUri 请求URL
     * @return 请求结果
     */
    public static String get(String requestUri) {
        return get(requestUri, null);
    }

    /**
     * http get请求
     *
     * @param requestUri  请求URL
     * @param headerParam 请求头参数
     * @return 请求结果
     */
    public static String get(String requestUri, Map<String, String> headerParam) {
        return get(requestUri, headerParam, "UTF-8");
    }

    /**
     * http get请求
     *
     * @param requestUri   请求URL
     * @param headerParam  请求头参数
     * @param resultEncode 返回值编码
     * @return 请求结果
     */
    public static String get(String requestUri, Map<String, String> headerParam, String resultEncode) {
        try {
            var httpGet = new HttpGet(requestUri);
            Optional.ofNullable(headerParam).orElse(new HashMap<>(16)).forEach(httpGet::setHeader);

            return execute(httpGet, resultEncode);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * http post application/x-www-form-urlencoded
     *
     * @param requestUri   请求URL
     * @param requestParam 请求参数
     * @return 请求结果
     */
    public static String post(String requestUri, Map<String, String> requestParam) {
        return post(requestUri, null, requestParam);
    }

    /**
     * http post application/x-www-form-urlencoded
     *
     * @param requestUri   请求URL
     * @param headerParam  请求头参数
     * @param requestParam 请求参数
     * @return 请求结果
     */
    public static String post(String requestUri, Map<String, String> headerParam, Map<String, String> requestParam) {
        return post(requestUri, headerParam, requestParam, "UTF-8", "UTF-8");
    }

    /**
     * http post application/x-www-form-urlencoded
     *
     * @param requestUri    请求URL
     * @param headerParam   请求头参数
     * @param requestParam  请求参数
     * @param requestEncode 请求参数编码
     * @param resultEncode  返回参数编码
     * @return 请求结果
     */
    public static String post(String requestUri, Map<String, String> headerParam, Map<String, String> requestParam, String requestEncode, String resultEncode) {
        try {
            var httpPost = new HttpPost(requestUri);
            Optional.ofNullable(headerParam).orElse(new HashMap<>(16)).forEach(httpPost::setHeader);
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            Optional.ofNullable(requestParam).orElse(new HashMap<>(16)).forEach((k, v) -> nameValuePairList.add(new BasicNameValuePair(k, v)));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, requestEncode));

            return execute(httpPost, resultEncode);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * http post application/json
     *
     * @param requestUri 请求URL
     * @param body  json对象
     * @return 请求结果
     */
    public static String postJson(String requestUri, Object body) {
        return postJson(requestUri, null, body);
    }

    /**
     * http post application/json
     *
     * @param requestUri  请求URL
     * @param headerParam 请求头参数
     * @param body   json对象
     * @return 请求结果
     */
    public static String postJson(String requestUri, Map<String, String> headerParam, Object body) {
        return postJson(requestUri, headerParam, body, ContentType.APPLICATION_JSON, "UTF-8");
    }

    /**
     * http post application/json
     *
     * @param requestUri   请求URL
     * @param headerParam  请求头参数
     * @param body    json对象
     * @param contentType  ContentType
     * @param resultEncode 返回值编码
     * @return 请求结果
     */
    public static String postJson(String requestUri, Map<String, String> headerParam, Object body, ContentType contentType, String resultEncode) {
        try {
            var httpPost = new HttpPost(requestUri);
            Optional.ofNullable(headerParam).orElse(new HashMap<>(16)).forEach(httpPost::setHeader);
            HttpEntity httpEntity = new StringEntity(Json.toJson(body), contentType);
            httpPost.setEntity(httpEntity);

            return execute(httpPost, resultEncode);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * http post form-data
     *
     * @param requestUri   请求url
     * @param requestParam 请求入参
     * @return 请求结果
     */
    public static String postFormData(String requestUri, Map<String, String> requestParam) {
        return postFormData(requestUri, null, requestParam);
    }

    /**
     * http post form-data
     *
     * @param requestUri   请求url
     * @param headerParam  请求头参数
     * @param requestParam 请求入参
     * @return 请求结果
     */
    public static String postFormData(String requestUri, Map<String, String> headerParam, Map<String, String> requestParam) {
        return postFormData(requestUri, headerParam, requestParam, null, null);
    }

    /**
     * http post form-data /multipart/form-data
     *
     * @param requestUri   请求url
     * @param headerParam  请求头参数
     * @param requestParam 请求参数
     * @param fileName     文件参数名
     * @param file         文件
     * @return 请求结果
     */
    public static String postFormData(String requestUri, Map<String, String> headerParam, Map<String, String> requestParam, String fileName, File file) {
        return postFormData(requestUri, headerParam, requestParam, fileName, file, "UTF-8", "UTF-8");
    }

    /**
     * http post form-data /multipart/form-data
     *
     * @param requestUri    请求url
     * @param headerParam   请求头参数
     * @param requestParam  请求参数
     * @param fileName      文件参数名
     * @param file          文件
     * @param requestEncode 请求参数编码
     * @param resultEncode  返回参数编码
     * @return 请求结果
     */
    public static String postFormData(String requestUri, Map<String, String> headerParam, Map<String, String> requestParam, String fileName, File file, String requestEncode, String resultEncode) {
        try {
            var multipartEntityBuilder = MultipartEntityBuilder.create();
            if (file != null && Util.isNotEmpty(fileName)) {
                multipartEntityBuilder.addBinaryBody(fileName, file).setMode(HttpMultipartMode.RFC6532);
            }

            Optional.ofNullable(requestParam).orElse(new HashMap<>(16)).forEach((k, v) -> multipartEntityBuilder.addPart(k, new StringBody(v, ContentType.create("text/plain", Charset.forName(requestEncode)))));
            var httpPost = new HttpPost(requestUri);
            Optional.ofNullable(headerParam).orElse(new HashMap<>(16)).forEach(httpPost::setHeader);
            httpPost.setEntity(multipartEntityBuilder.build());

            return execute(httpPost, resultEncode);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }


    /**
     * 根据url获取远程文件资源
     *
     * @param url 访问地址
     * @return byte数组
     */
    public static byte[] getByteFromUrl(String url) {
        try {
            var get = new HttpGet(url);
            var res = getHttpClient().execute(get);
            if (res.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new BusinessException(res.toString());
            }

            return EntityUtils.toByteArray(res.getEntity());
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
