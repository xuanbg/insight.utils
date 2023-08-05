package com.insight.utils.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.insight.utils.excel.ColumnName.Policy.None;

/**
 * @author 宣炳刚
 * @date 2017/11/9
 * @remark 自定义列名注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {

    /**
     * 策略枚举
     */
    enum Policy {

        /**
         * 默认值
         */
        None,

        /**
         * 必需的(Sheet中必须包含该列)
         */
        Required,

        /**
         * 可忽略的(不会被导出)
         */
        Ignorable
    }

    /**
     * 读取列名称
     *
     * @return 名称
     */
    String value();

    /**
     * 读取列日期格式
     *
     * @return 日期格式
     */
    String dateFormat() default "yyyy-MM-dd";

    /**
     * 读取列策略
     *
     * @return 策略
     */
    Policy polic() default None;
}
