package net.bodz.bas.db.ibatis.type;

import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.type.JdbcType;
import org.json.JSONObject;
import org.json.JSONWriter;

import net.bodz.bas.db.ibatis.TypeHandler;

public abstract class AbstractMapTypeHandler<map_t extends Map<K, V>, K, V>
        extends TypeHandler<map_t> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, map_t param, JdbcType jdbcType)
            throws SQLException {
        String json = param == null ? null : toJson(param);
        ps.setString(i, json);
    }

    @Override
    public map_t getNullableResult(CallableStatement cs, int i)
            throws SQLException {
        String json = cs.getString(i);
        return parse(json);
    }

    @Override
    public map_t getNullableResult(ResultSet rs, int i)
            throws SQLException {
        String json = rs.getString(i);
        return parse(json);
    }

    @Override
    public map_t getNullableResult(ResultSet rs, String s)
            throws SQLException {
        String json = rs.getString(s);
        return parse(json);
    }

    protected map_t parse(String json) {
        if (json == null)
            return null;
        JSONObject obj = new JSONObject(json);
        map_t map = createMap();
        for (Object _keyStr : obj.keySet()) {
            String keyStr = (String) _keyStr;
            K key = parseKey(keyStr);
            Object valObj = obj.get(keyStr);
            V val = parseValue(valObj);
            map.put(key, val);
        }
        return map;
    }

    protected abstract map_t createMap();

    protected abstract K parseKey(String str);

    protected abstract V parseValue(Object obj);

    static String toJson(Map<?, ?> map) {
        StringWriter buf = new StringWriter();
        JSONWriter out = new JSONWriter(buf);
        out.object();
        for (Entry<?, ?> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Object val = entry.getValue();
            out.key(key);
            out.value(val);
        }
        out.endObject();
        return buf.toString();
    }

}
