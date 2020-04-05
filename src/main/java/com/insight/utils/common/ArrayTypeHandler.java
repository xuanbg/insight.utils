package com.insight.utils.common;

import com.insight.utils.Json;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 宣炳刚
 * @date 2018/8/2
 * @remark 数据库json字段转换集合
 */
public class ArrayTypeHandler<T> extends BaseTypeHandler<T> {
    private Class<T> clazz;

    /**
     * 构造函数
     *
     * @param clazz 类型
     */
    public ArrayTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }

    /**
     * Set方法
     *
     * @param ps        PreparedStatement
     * @param i         下标
     * @param parameter 参数
     * @param jdbcType  类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, Json.toJson(parameter));
    }

    /**
     * Get方法
     *
     * @param rs         ResultSet
     * @param columnName 字段名
     * @return 对象实体
     */
    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Json.toList(rs.getString(columnName), clazz);
    }

    /**
     * Get方法
     *
     * @param rs          ResultSet
     * @param columnIndex 字段下标
     * @return 对象实体
     */
    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Json.toList(rs.getString(columnIndex), clazz);
    }

    /**
     * Get方法
     *
     * @param cs          CallableStatement
     * @param columnIndex 字段下标
     * @return 对象实体
     */
    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Json.toList(cs.getString(columnIndex), clazz);
    }
}
