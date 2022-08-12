package net.bodz.bas.c.org.json;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;

@Alias("JsonValue")
@AliasedType
@MappedTypes(JsonValueWrapper.class)
public class JsonValueTypeHandler
        extends TypeHandler<JsonValueWrapper> {

    public JsonValueTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonValueWrapper parameter, JdbcType jdbcType)
            throws SQLException {
        String json = parameter.toJsonStr(JsonFormOptions.SQL);
        PGobject pgo = new PGobject();
        pgo.setType("json"); // jsonb .. similar.
        pgo.setValue(json);
        ps.setObject(i, pgo);
    }

    @Override
    public JsonValueWrapper getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String json = rs.getString(columnName);
        JsonValueWrapper val = new JsonValueWrapper();
        try {
            val.fromJsonStr(json, JsonFormOptions.SQL);
        } catch (ParseException e) {
            throw new SQLException("Failed to parse: " + json, e);
        }
        return val;
    }

    @Override
    public JsonValueWrapper getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String jsonData = rs.getString(columnIndex);
        JsonValueWrapper val = new JsonValueWrapper();
        try {
            val.fromJsonStr(jsonData, JsonFormOptions.SQL);
        } catch (ParseException e) {
            throw new SQLException("Failed to parse: " + jsonData, e);
        }
        return val;
    }

    @Override
    public JsonValueWrapper getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String json = cs.getString(columnIndex);
        JsonValueWrapper val = new JsonValueWrapper();
        try {
            val.fromJsonStr(json, JsonFormOptions.SQL);
        } catch (ParseException e) {
            throw new SQLException("Failed to parse: " + json, e);
        }
        return val;
    }

}
