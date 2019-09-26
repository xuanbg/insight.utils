package com.insight.util.common;

import com.fasterxml.jackson.databind.JavaType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 宣炳刚
 * @date 2019/9/26
 * @remark
 */
public class RealType<T> {

    // 使用反射技术得到T的真实类型
    public JavaType getRealType(){
        // 获取当前new的对象的泛型的父类类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] type = pt.getActualTypeArguments();

        // 获取第一个类型参数的真实类型
        return (JavaType) type[0];
    }
}
