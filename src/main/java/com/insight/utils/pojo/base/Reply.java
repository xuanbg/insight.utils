package com.insight.utils.pojo.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.Json;

import java.util.List;

/**
 * @author 作者
 * @date 2017年9月5日
 * @remark Reply封装
 */
public final class Reply extends BaseXo {

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
        return code >= 200 && code < 300;
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
        return Json.toBean(data, type);
    }

    /**
     * 读取Data承载中的对象集合数据
     *
     * @param type 对象类型
     * @param <T>  泛型参数
     * @return 对象集合数据
     */
    @JsonIgnore
    public <T> List<T> getListFromData(Class<T> type) {
        return Json.toList(data, type);
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
        return Json.toBean(option, type);
    }

    /**
     * 读取Option中承载的对象集合数据
     *
     * @param type 对象类型
     * @param <T>  泛型参数
     * @return 对象集合数据
     */
    @JsonIgnore
    public <T> List<T> getListFromOption(Class<T> type) {
        return Json.toList(option, type);
    }
}
