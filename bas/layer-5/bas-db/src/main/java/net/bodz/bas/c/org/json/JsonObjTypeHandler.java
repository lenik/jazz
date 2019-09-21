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

@Alias("JsonObj")
@AliasedType
@MappedTypes(JsonObj.class)
public class JsonObjTypeHandler
        extends TypeHandler<JsonObj> {

    public JsonObjTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonObj parameter, JdbcType jdbcType)
            throws SQLException {
        String json = parameter.readInStr();
        PGobject pgo = new PGobject();
        pgo.setType("json"); // jsonb .. similar.
        pgo.setValue(json);
        ps.setObject(i, pgo);
    }

    @Override
    public JsonObj getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String json = rs.getString(columnName);
        JsonObj val = new JsonObj();
        try {
            val.writeInStr(json);
        } catch (ParseException e) {
            throw new SQLException("Failed to parse: " + json, e);
        }
        return val;
    }

    @Override
    public JsonObj getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String json = rs.getString(columnIndex);
        JsonObj val = new JsonObj();
        try {
            val.writeInStr(json);
        } catch (ParseException e) {
            throw new SQLException("Failed to parse: " + json, e);
        }
        return val;
    }

    @Override
    public JsonObj getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String json = cs.getString(columnIndex);
        JsonObj val = new JsonObj();
        try {
            val.writeInStr(json);
        } catch (ParseException e) {
            throw new SQLException("Failed to parse: " + json, e);
        }
        return val;
    }

}
