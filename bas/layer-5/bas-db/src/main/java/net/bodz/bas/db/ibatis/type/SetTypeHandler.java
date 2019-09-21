package net.bodz.bas.db.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Set;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;

@Alias("Set")
@AliasedType
@MappedTypes(Set.class)
public class SetTypeHandler
        extends TypeHandler<Set<Object>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<Object> parameter, JdbcType jdbcType)
            throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Object o : parameter) {
            if (sb.length() > 1)
                sb.append(", ");
            sb.append(o);
        }
        sb.append(")");
        ps.setObject(i, sb.toString(), Types.OTHER);
    }

    @Override
    public Set<Object> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return null;
    }

    @Override
    public Set<Object> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return null;
    }

    @Override
    public Set<Object> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return null;
    }

}
