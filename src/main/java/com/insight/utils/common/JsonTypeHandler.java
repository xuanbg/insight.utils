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
 * @date 2018/5/16
 * @remark 数据库json字段转换对象
 */
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {
    private Class<T> clazz;

    /**
     * 构造方法
     *
     * @param clazz
     */
    public JsonTypeHandler(Class<T> clazz) {
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
        return Json.toBean(rs.getString(columnName), clazz);
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
        return Json.toBean(rs.getString(columnIndex), clazz);
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
        return Json.toBean(cs.getString(columnIndex), clazz);
    }
}
