package net.bodz.bas.c.org.json;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.json.JsonObject;

/**
 * @see JsonStrFormTypeHandler
 */
@Alias("PojoJson")
@AliasedType
@MappedTypes(Map.class)
public class PojoJsonTypeHandler
        extends TypeHandler<Map<String, Object>> {

    public PojoJsonTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> parameter, JdbcType jdbcType)
            throws SQLException {
        JsonObject obj = new JsonObject(parameter);
        String json = obj.toString();
        PGobject pgo = new PGobject();
        pgo.setType("json"); // jsonb .. similar.
        pgo.setValue(json);
        ps.setObject(i, pgo);
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String json = rs.getString(columnName);
        return JsonFn.toMap(json);
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String json = rs.getString(columnIndex);
        return JsonFn.toMap(json);
    }

    @Override
    public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String json = cs.getString(columnIndex);
        return JsonFn.toMap(json);
    }

}
