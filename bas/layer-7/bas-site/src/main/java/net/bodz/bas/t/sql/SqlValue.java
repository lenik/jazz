package net.bodz.bas.t.sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.bodz.bas.db.sql.dialect.ISqlFormat;

public class SqlValue {

    public SqlValueType type = SqlValueType.DEFAULT;

    public Object value;
    public String formatSpec = "%s";

    public SqlValue(SqlValueType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public String toSqlCode(ISqlFormat format) {
        return type.toSqlCode(format, value);
    }

    public void setParameter(PreparedStatement ps, int index)
            throws SQLException {
        ps.setObject(index, value);
    }

    public void setParameter(CallableStatement cs, int index)
            throws SQLException {
        cs.setObject(index, value);
    }
    //

    public static SqlValue atom(Object val) {
        return new SqlValue(SqlValueType.DEFAULT, val);
    }

    public static SqlValue quoted(Object val) {
        return new SqlValue(SqlValueType.STRING, val);
    }

    public static SqlValue name(String name) {
        return new SqlValue(SqlValueType.NAME, name);
    }

    public static SqlValue raw(String sqlCode) {
        return new SqlValue(SqlValueType.RAW, sqlCode);
    }

    public static SqlValue format(String format, Object sqlCode) {
        SqlValue o = new SqlValue(SqlValueType.RAW, sqlCode);
        o.formatSpec = format;
        return o;
    }

}
