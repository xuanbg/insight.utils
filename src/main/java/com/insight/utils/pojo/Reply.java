package com.insight.utils.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Json;

/**
 * @author 作者
 * @date 2017年9月5日
 * @remark Reply封装
 */
public final class Reply extends BaseXo {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 可选数据
     */
    private Object option;

    /**
     * 无参构造函数
     */
    public Reply() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getOption() {
        return option;
    }

    public void setOption(Object option) {
        this.option = option;
    }

    /**
     * 读取Data承载中的对象数据
     *
     * @param type 对象类型
     * @param <T>  泛型参数
     * @return 对象数据
     */
    @JsonIgnore
    public <T> T getBeanFromData(Class<T> type) {
        String json = Json.toJson(data);
        return Json.toBean(json, type);
    }

    /**
     * 读取Data承载中的对象集合数据
     *
     * @param type 对象类型
     * @param <T>  泛型参数
     * @return 对象集合数据
     */
    @JsonIgnore
    public <T> T getListFromData(Class<?>... type) {
        String json = Json.toJson(data);
        return Json.toList(json, type);
    }

    /**
     * 读取Option中承载的对象数据
     *
     * @param type 对象类型
     * @param <T>  泛型参数
     * @return 对象数据
     */
    @JsonIgnore
    public <T> T getBeanFromOption(Class<T> type) {
        String json = Json.toJson(option);
        return Json.toBean(json, type);
    }

    /**
     * 读取Option中承载的对象集合数据
     *
     * @param type 对象类型
     * @param <T>  泛型参数
     * @return 对象集合数据
     */
    @JsonIgnore
    public <T> T getListFromOption(Class<?>... type) {
        String json = Json.toJson(option);
        return Json.toList(json, type);
    }
}
