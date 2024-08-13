package net.bodz.lily.concrete.util;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;

@Alias("TextObject")
@AliasedType
@MappedTypes(TextObject.class)
public class TextObjectTypeHandler
        extends TypeHandler<TextObject> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TextObject parameter, JdbcType jdbcType)
            throws SQLException {
        if (jdbcType == null)
            jdbcType = JdbcType.CLOB;
        switch (jdbcType) {
        case DATE:
            Date date = Date.valueOf(parameter.getText());
            ps.setDate(i, date);
            break;

        case TIME:
            Time time = Time.valueOf(parameter.getText());
            ps.setTime(i, time);
            break;

        case TIMESTAMP:
            Timestamp timestamp = Timestamp.valueOf(parameter.getText());
            ps.setTimestamp(i, timestamp);
            break;

        default:
            ps.setString(i, parameter.getText());
        }
    }

    @Override
    public TextObject getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String str = rs.getString(columnName);
        if (str == null)
            return null;
        else
            return new TextObject(str);
    }

    @Override
    public TextObject getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String str = rs.getString(columnIndex);
        if (str == null)
            return null;
        else
            return new TextObject(str);
    }

    @Override
    public TextObject getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String str = cs.getString(columnIndex);
        if (str == null)
            return null;
        else
            return new TextObject(str);
    }

}
