package net.bodz.bas.db.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;

@Alias("ZoneId")
@AliasedType
@MappedTypes(ZoneId.class)
public class ZoneIdTypeHandler
        extends TypeHandler<ZoneId> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ZoneId parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getId());
    }

    @Override
    public ZoneId getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String id = rs.getString(columnName);
        if (id == null)
            return null;
        else
            return ZoneId.of(id);
    }

    @Override
    public ZoneId getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String id = rs.getString(columnIndex);
        if (id == null)
            return null;
        else
            return ZoneId.of(id);
    }

    @Override
    public ZoneId getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String id = cs.getString(columnIndex);
        if (id == null)
            return null;
        else
            return ZoneId.of(id);
    }

}
