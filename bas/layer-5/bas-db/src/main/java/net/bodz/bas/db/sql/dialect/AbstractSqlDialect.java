package net.bodz.bas.db.sql.dialect;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.db.sql.DataType;
import net.bodz.bas.db.sql.DataTypes;
import net.bodz.bas.db.sql.IStdDataTypes;
import net.bodz.bas.db.sql.SQLLangs;

public abstract class AbstractSqlDialect
        implements ISqlDialect,
                   IStdDataTypes {

    protected String nullLiteral = "null";

    //    List<DataType> _dataTypeList = new ArrayList<>();
    protected final DataTypes dataTypes = new DataTypes();

    public AbstractSqlDialect() {
        declareTypes();
    }

    protected void declareTypes() {
        dataTypes.add(BIT, BOOLEAN);
        dataTypes.add(TINYINT, SMALLINT, INTEGER, BIGINT);
        dataTypes.add(FLOAT, REAL, DOUBLE);
        dataTypes.add(NUMERIC, DECIMAL);
        dataTypes.add(CHAR, VARCHAR, LONGVARCHAR);
        dataTypes.add(NCHAR, NVARCHAR, LONGNVARCHAR);
        dataTypes.add(BINARY, VARBINARY, LONGVARBINARY);
        dataTypes.add(BLOB, CLOB, NCLOB);
        dataTypes.add(DATE, TIME, TIMESTAMP);
        dataTypes.add(TIME_WITH_TIMEZONE, TIMESTAMP_WITH_TIMEZONE);
        dataTypes.add(NULL, ROWID, SQLXML, JAVA_OBJECT);
        dataTypes.add(REF, DATALINK, REF_CURSOR);
        dataTypes.add(OTHER, DISTINCT, STRUCT, ARRAY);
    }

    @Override
    public String getStatementTerminator() {
        return ";";
    }

    @Override
    public String getNullLiteral() {
        return nullLiteral;
    }

    @Override
    public boolean isReservedKeyword(String token) {
        if (token == null)
            return false;
        token = token.toLowerCase();
        return SQLLangs.SQL.isReservedKeyword(token);
    }

    @Override
    public String qName(String name) {
        return StringQuote.qq(name);
    }

    @Override
    public String qNameSmart(String name) {
        if (isReservedKeyword(name))
            return qName(name);
        else
            return name;
    }

    static final String doubleSingleQuote = //
            StringQuote.singleQuote + StringQuote.singleQuote;

    @Override
    public String qString(String s) {
        if (s == null)
            return nullLiteral;
        s = s.replace(StringQuote.singleQuote, doubleSingleQuote);
        return StringQuote.q(s);
    }

    @Override
    public String toDate(String dateStr, String sqlDateFormat) {
        String sql = "to_date(" + qString(dateStr) + ", " + qString(sqlDateFormat) + ")";
        return sql;
    }

    protected String qRawDate(String rawDateLiteral) {
        if (rawDateLiteral == null)
            return nullLiteral;
        else
            return StringQuote.q(rawDateLiteral);
    }

    /**
     * @see DateTimeFormatter
     */
    @Override
    public String sqlDateFormat(String javaDateTimeFormat) {
        return javaDateTimeFormat;
    }

    DateTimeSpec dateTimeSpec(String javaDateTimeFormat) {
        String sqlDateFormat = sqlDateFormat(javaDateTimeFormat);
        return new DateTimeSpec(javaDateTimeFormat, sqlDateFormat);
    }

    DateTimeSpec dateSpec = dateTimeSpec("yyyy-MM-dd");
    DateTimeSpec timeSpec = dateTimeSpec("HH:mm:ss");

    DateTimeSpec dateTimeSpec = dateTimeSpec("yyyy-MM-dd HH:mm:ss.SSS");
    DateTimeSpec dateTimeWithZoneIdSpec = dateTimeSpec("yyyy-MM-dd HH:mm:ss.SSS zzz");
    DateTimeSpec dateTimeWithZoneOffsetSpec = dateTimeSpec("yyyy-MM-dd HH:mm:ss.SSS zzz");
    DateTimeSpec timeWithZoneOffsetSpec = dateTimeSpec("HH:mm:ss.SSS zzz");

    @Override
    public final String qDate(Date date) {
        DateTimeSpec spec = dateTimeSpec;
        Instant instant = date.toInstant();
        String str = spec.format(instant);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qDate(java.sql.Date date) {
        DateTimeSpec spec = dateSpec;
        Instant instant = date.toInstant();
        String str = spec.format(instant);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qTime(Time time) {
        LocalTime localTime = time.toLocalTime();
        return qLocalTime(localTime);
    }

    @Override
    public String qInstant(Instant instant) {
        DateTimeSpec spec = dateTimeSpec;
        String str = spec.format(instant);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qZonedDateTime(ZonedDateTime zonedDateTime) {
        DateTimeSpec spec = dateTimeWithZoneIdSpec;
        String str = spec.format(zonedDateTime);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qOffsetDateTime(OffsetDateTime offsetDateTime) {
        DateTimeSpec spec = dateTimeWithZoneOffsetSpec;
        String str = spec.format(offsetDateTime);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qOffsetTime(OffsetTime offsetTime) {
        DateTimeSpec spec = timeWithZoneOffsetSpec;
        String str = spec.format(offsetTime);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qLocalDateTime(LocalDateTime localDateTime) {
        DateTimeSpec spec = dateTimeSpec;
        String str = spec.format(localDateTime);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qLocalDate(LocalDate localDate) {
        DateTimeSpec spec = dateSpec;
        String str = spec.format(localDate);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qLocalTime(LocalTime localTime) {
        return qString(localTime.toString());
    }

    @Override
    public DataType getDefaultType(int sqlType) {
        return dataTypes.getDefault(sqlType);
    }

    @Override
    public DataType getDefaultType(String sqlTypeName) {
        return dataTypes.getDefault(sqlTypeName);
    }

    @Override
    public DataType getDefaultType(int sqlType, String sqlTypeName) {
        if (sqlTypeName != null) {
            DataType type = getDefaultType(sqlTypeName);
            if (type != null)
                return type;
        }
        return getDefaultType(sqlType);
    }

    @Override
    public DataType getDefaultType(Class<?> javaClass) {
        Class<?> boxed = Primitives.box(javaClass);
        return dataTypes.getDefault(boxed);
    }

}
