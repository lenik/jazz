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

@Alias("JsonStrForm")
@AliasedType
@MappedTypes(IJsonStrForm.class)
public class JsonStrFormTypeHandler
        extends TypeHandler<IJsonStrForm> {

    public JsonStrFormTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IJsonStrForm parameter, JdbcType jdbcType)
            throws SQLException {
        String json = parameter.toJsonStr();
        PGobject pgo = new PGobject();
        pgo.setType("json"); // jsonb .. similar.
        pgo.setValue(json);
        ps.setObject(i, pgo);
    }

    @Override
    public IJsonStrForm getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String json = rs.getString(columnName);
        JsonStr val = new JsonStr();
        val.fromJsonStr_sql(json);
        return val;
    }

    @Override
    public IJsonStrForm getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String json = rs.getString(columnIndex);
        JsonStr val = new JsonStr();
        val.fromJsonStr_sql(json);
        return val;
    }

    @Override
    public IJsonStrForm getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String json = cs.getString(columnIndex);
        JsonStr val = new JsonStr();
        val.fromJsonStr_sql(json);
        return val;
    }

}
