package net.bodz.bas.db.sql;

import java.sql.Timestamp;
import java.util.Date;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.t.catalog.JdbcType;

public class DDLExporter {

    public static boolean trim = true;

    protected static String encode(Object val, JdbcType type) {
        if (val == null)
            return "null";

        switch (type) {
        case CHAR:
        case NCHAR:
        case VARCHAR:
        case NVARCHAR:
        case CLOB:
        case NCLOB:
            String s = val.toString();
            if (trim)
                s = s.trim();
            s = s.replace("'", "''");
            return StringQuote.q(s);

        case NUMERIC:
            // lose precision?
            return val.toString();

        case DATE:
            Date date = (Date) val;
            String dateStr = Dates.ISO8601.format(date);
            return StringQuote.q(dateStr);

        case TIMESTAMP:
            Timestamp timestamp = (Timestamp) val;
            String tsStr = Dates.ISO8601.format(timestamp);
            return StringQuote.q(tsStr);

        default:
            return val.toString();
        }
    }

}
