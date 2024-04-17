package net.bodz.bas.db.sql;

import java.sql.Timestamp;

import net.bodz.bas.c.java.time.DateTimes;
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
            String dateStr = DateTimes.ISO_LOCAL_DATE.format(DateTimes.convert((java.sql.Date) val));
            return StringQuote.q(dateStr);

        case TIME:
            String timeStr = DateTimes.ISO_LOCAL_TIME.format(DateTimes.convert((java.sql.Time) val));
            return StringQuote.q(timeStr);

        case TIMESTAMP:
            String tsStr = DateTimes.ISO8601.format(DateTimes.convert((Timestamp) val));
            return StringQuote.q(tsStr);

        default:
            return val.toString();
        }
    }

}
