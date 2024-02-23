package net.bodz.bas.db.ibatis.type;

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
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;

@Alias("JsonVar")
@AliasedType
@MappedTypes(JsonVariant.class)
public class JsonVariantTypeHandler
        extends TypeHandler<JsonVariant> {

    JsonFormOptions JSON_SQL = JsonFormOptions.SQL;

    public JsonVariantTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonVariant parameter, JdbcType jdbcType)
            throws SQLException {
        String json = parameter.toJson();
        PGobject pgo = new PGobject();
        pgo.setType("json"); // jsonb .. similar.
        pgo.setValue(json);
        ps.setObject(i, pgo);
    }

    static JsonVariant parse(String json)
            throws SQLException {
        return parse(json, false);
    }

    static JsonVariant parse(String json, boolean wrapNull)
            throws SQLException {
        if (json == null)
            if (wrapNull)
                return JsonVariant.NULL;
            else
                return null;
        try {
            return JsonFn.parseAny(json);
        } catch (ParseException e) {
            throw new SQLException("Error parse json: " + e.getMessage(), e);
        }
    }

    @Override
    public JsonVariant getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String json = rs.getString(columnName);
        return parse(json);
    }

    @Override
    public JsonVariant getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String json = rs.getString(columnIndex);
        return parse(json);
    }

    @Override
    public JsonVariant getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String json = cs.getString(columnIndex);
        return parse(json);
    }

}
