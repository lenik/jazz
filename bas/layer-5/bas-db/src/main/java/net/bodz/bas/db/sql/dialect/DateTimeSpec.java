package net.bodz.bas.db.sql.dialect;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateTimeSpec {

    public final String javaSpec;
    public final String sqlSpec;

    public final DateTimeFormatter dateTimeFormatter;

    public DateTimeSpec(String javaSpec, String sqlSpec) {
        this.javaSpec = javaSpec;
        this.sqlSpec = sqlSpec;
        dateTimeFormatter = DateTimeFormatter.ofPattern(javaSpec);
    }

    public String format(TemporalAccessor temporal) {
        return dateTimeFormatter.format(temporal);
    }

}
