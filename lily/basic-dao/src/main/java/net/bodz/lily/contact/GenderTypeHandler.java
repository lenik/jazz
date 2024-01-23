package net.bodz.lily.contact;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.TypeHandler;

@MappedTypes(Gender.class)
public class GenderTypeHandler
        extends TypeHandler<Gender> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Gender parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getKey().toString());
    }

    @Override
    public Gender getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String s = rs.getString(columnName);
        if (s == null)
            return Gender.UNKNOWN;
        else
            return Gender.meta.ofKey(s.charAt(0));
    }

    @Override
    public Gender getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String s = rs.getString(columnIndex);
        if (s == null)
            return Gender.UNKNOWN;
        else
            return Gender.meta.ofKey(s.charAt(0));
    }

    @Override
    public Gender getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String s = cs.getString(columnIndex);
        if (s == null)
            return Gender.UNKNOWN;
        else
            return Gender.meta.ofKey(s.charAt(0));
    }

}
