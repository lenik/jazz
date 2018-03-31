package net.bodz.bas.c.org.json;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import net.bodz.bas.db.ibatis.AliasedType;
import net.bodz.bas.db.ibatis.TypeHandler;

@Alias("JsonForm")
@AliasedType
public class JsonFormTypeHandler
        extends TypeHandler<IJsonForm> {

    public JsonFormTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IJsonForm parameter, JdbcType jdbcType)
            throws SQLException {
        String json = parameter.readInStr();
        PGobject pgo = new PGobject();
        pgo.setType("json"); // jsonb .. similar.
        pgo.setValue(json);
        ps.setObject(i, pgo);
    }

    @Override
    public IJsonForm getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String json = rs.getString(columnName);
        JsonStr val = new JsonStr();
        val.writeInStr_sql(json);
        return val;
    }

    @Override
    public IJsonForm getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String json = rs.getString(columnIndex);
        JsonStr val = new JsonStr();
        val.writeInStr_sql(json);
        return val;
    }

    @Override
    public IJsonForm getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String json = cs.getString(columnIndex);
        JsonStr val = new JsonStr();
        val.writeInStr_sql(json);
        return val;
    }

}
