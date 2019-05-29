package com.insight.util.common;


import com.insight.util.Json;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 宣炳刚
 * @date 2018/5/16
 * @remark 数据库json字段 与实体类 参数类型 对应
 */
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {
    private Class<T> clazz;

    public JsonTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, Json.toJson(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Json.toBean(rs.getString(columnName), clazz);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Json.toBean(rs.getString(columnIndex), clazz);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Json.toBean(cs.getString(columnIndex), clazz);
    }
}
