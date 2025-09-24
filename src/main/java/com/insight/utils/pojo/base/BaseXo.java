package com.insight.utils.pojo.base;

import com.insight.utils.Json;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2021/4/23
 * @remark VO/DTO基类
 */
public class BaseXo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;

    /**
     * 类型转换
     *
     * @param type 指定的数据类型
     * @param <T>  泛型参数
     * @return 指定类型数据对象
     */
    public <T> T convert(Class<T> type) {
        return Json.toBean(this, type);
    }

    /**
     * toString
     *
     * @return 对象序列化后的数据
     */
    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
