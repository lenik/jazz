package net.bodz.bas.db.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;

@Alias("UUID")
@AliasedType
@MappedTypes(UUID.class)
public class UUIDTypeHandler
        extends TypeHandler<UUID> {

    public static final int UUID_SINCE = 9;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType)
            throws SQLException {
        int major = ps.getConnection().getMetaData().getDatabaseMajorVersion();
        if (major >= UUID_SINCE) {
            ps.setObject(i, parameter);
        } else {
            String uuidStr = parameter.toString();
            ps.setString(i, uuidStr);
        }
    }

    @Override
    public UUID getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        int major = rs.getStatement().getConnection().getMetaData().getDatabaseMajorVersion();
        if (major >= UUID_SINCE) {
            Object val = rs.getObject(columnName);
            return (UUID) val;
        } else {
            String str = rs.getString(columnName);
            return UUID.fromString(str);
        }
    }

    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        int major = rs.getStatement().getConnection().getMetaData().getDatabaseMajorVersion();
        if (major >= UUID_SINCE) {
            Object val = rs.getObject(columnIndex);
            return (UUID) val;
        } else {
            String str = rs.getString(columnIndex);
            return UUID.fromString(str);
        }
    }

    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        int major = cs.getConnection().getMetaData().getDatabaseMajorVersion();
        if (major >= UUID_SINCE) {
            Object val = cs.getObject(columnIndex);
            return (UUID) val;
        } else {
            String str = cs.getString(columnIndex);
            return UUID.fromString(str);
        }
    }

}