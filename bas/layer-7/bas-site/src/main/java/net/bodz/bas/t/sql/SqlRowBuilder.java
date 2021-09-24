package net.bodz.bas.t.sql;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import net.bodz.bas.db.sql.dialect.ISqlFormat;
import net.bodz.bas.repr.form.SortOrder;

public class SqlRowBuilder
        implements
            ISqlRowOutput {

    // List<Pair<String, Object>> pairs = new ArrayList<>();
    Map<String, SqlValue> pairs;

    ISqlFormat format;
    boolean prepared;
    boolean named;

    String term = ";";

    public SqlRowBuilder(ISqlFormat format) {
        this(format, SortOrder.KEEP);
    }

    public SqlRowBuilder(ISqlFormat format, SortOrder order) {
        this.format = format;
        pairs = order.newMap();
    }

    public SqlRowBuilder prepared() {
        prepared = true;
        return this;
    }

    public SqlRowBuilder prepared(boolean named) {
        prepared = true;
        this.named = named;
        return this;
    }

    public SqlRowBuilder named() {
        prepared = true;
        named = true;
        return this;
    }

    @Override
    public void putEntry(String name, Object value) {
        SqlValue o;
        if (value instanceof String) {
            o = SqlValue.quoted(value);
        } else {
            o = SqlValue.atom(value);
        }
        pairs.put(name, o);
    }

    @Override
    public void putDateEntry(String name, Date date) {
        SqlValue o = new SqlValue(SqlValueType.DATE, date);
        pairs.put(name, o);
    }

    String qDomain(String dn) {
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(dn, ".");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (sb.length() != 0)
                sb.append(".");
            sb.append(format.qName(token));
        }
        return sb.toString();
    }

    public String insertInto(String tableName) {
        StringBuilder head = new StringBuilder();
        StringBuilder tail = new StringBuilder();

        String qTableName = qDomain(tableName);
        head.append("insert into " + qTableName + "(");
        int i = 0;
        for (Entry<String, SqlValue> pair : pairs.entrySet()) {
            if (i++ != 0) {
                head.append(", ");
                tail.append(", ");
            }
            String name = pair.getKey();
            head.append(format.qName(name));

            SqlValue val = pair.getValue();
            if (prepared)
                if (named)
                    tail.append(":" + name);
                else
                    tail.append("?");
            else
                tail.append(val.toSqlCode(format));
        }
        head.append(") values(");
        head.append(tail);
        head.append(")");
        head.append(term);
        return head.toString();
    }

    public String update(String tableName) {
        StringBuilder head = new StringBuilder();

        String qTableName = qDomain(tableName);
        head.append("update " + qTableName + " set ");
        int i = 0;
        for (Entry<String, SqlValue> pair : pairs.entrySet()) {
            if (i++ != 0) {
                head.append(", ");
            }
            String name = pair.getKey();
            head.append(format.qName(name));
            head.append("=");

            SqlValue val = pair.getValue();
            if (prepared)
                if (named)
                    head.append(":" + name);
                else
                    head.append("?");
            else
                head.append(val.toSqlCode(format));
        }
        head.append(term);
        return head.toString();
    }

    public void apply(PreparedStatement statement)
            throws SQLException {
        int i = 0;
        for (Entry<String, SqlValue> pair : pairs.entrySet()) {
            SqlValue val = pair.getValue();
            statement.setObject(i++, val.value);
        }
    }

    public void applyNamed(CallableStatement statement)
            throws SQLException {
        for (Entry<String, SqlValue> pair : pairs.entrySet()) {
            String name = pair.getKey();
            SqlValue val = pair.getValue();
            statement.setObject(name, val.value);
        }
    }

}
