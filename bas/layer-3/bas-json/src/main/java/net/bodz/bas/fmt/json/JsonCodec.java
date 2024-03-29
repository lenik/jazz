package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Date;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.fork.org.json.JSONException;

public class JsonCodec {

    static final Logger logger = LoggerFactory.getLogger(JsonCodec.class);

    public static Object encodeSimpleTypes(Class<?> type, Object obj)
            throws IOException, FormatException {
        switch (TypeKind.getTypeId(type)) {
        case TypeId.BYTE:
        case TypeId.SHORT:
        case TypeId.INTEGER:
        case TypeId.LONG:
            return ((Number) obj).longValue();

        case TypeId.FLOAT:
            float fval = ((Number) obj).floatValue();
            if (Float.isInfinite(fval))
                return "Infinity";
            if (Float.isNaN(fval))
                return "NaN";
            return fval;

        case TypeId.DOUBLE:
            double dval = ((Number) obj).doubleValue();
            if (Double.isInfinite(dval))
                return "Infinity";
            if (Double.isNaN(dval))
                return "NaN";
            try {
                return dval;
            } catch (JSONException e) {
                throw e;
            }

        case TypeId.BOOLEAN:
            return ((Boolean) obj).booleanValue();

        case TypeId.STRING:
        case TypeId.STRING_BUFFER:
        case TypeId.STRING_BUILDER:
            return obj.toString();

        case TypeId.SQL_DATE:
            try {
                return DateTimes.ISO_LOCAL_DATE.format(DateTimes.convert((java.sql.Date) obj));
            } catch (Exception e) {
                throw new FormatException(e);
            }

        case TypeId.SQL_TIME:
            return DateTimes.ISO_LOCAL_DATE.format(DateTimes.convert((java.sql.Time) obj));

        case TypeId.DATE:
            String dateStr = DateTimes.ISO8601.format(DateTimes.convertDate((Date) obj));
            return dateStr;

        case TypeId.INSTANT:
            Instant instant = (Instant) obj;
            return instant.toEpochMilli();
        case TypeId.LOCAL_DATE_TIME:
            return DateTimes.ISO_LOCAL_DATE_TIME.format((LocalDateTime) obj);
        case TypeId.LOCAL_DATE:
            return DateTimes.ISO_LOCAL_DATE.format((LocalDate) obj);
        case TypeId.LOCAL_TIME:
            return DateTimes.ISO_LOCAL_TIME.format((LocalTime) obj);
        case TypeId.OFFSET_DATE_TIME:
            return DateTimes.ISO_OFFSET_DATE_TIME.format((OffsetDateTime) obj);
        case TypeId.OFFSET_TIME:
            return DateTimes.ISO_OFFSET_TIME.format((OffsetTime) obj);
        case TypeId.ZONED_DATE_TIME:
            return DateTimes.ISO_ZONED_DATE_TIME.format((ZonedDateTime) obj);

//        case TypeId.JODA_DATETIME:
//            AbstractDateTime jodaDateTime = (AbstractDateTime) obj;
//            String jodaDateStr = ISODateTimeFormat.dateTime().print(jodaDateTime);
//            return jodaDateStr;
        }
        return null;
    }

    public static Object encodeTextForm(Class<?> type, Object obj)
            throws IOException, FormatException {
        if (type == null)
            throw new NullPointerException("type");
        if (obj == null)
            return null;

        if (iString.class.isAssignableFrom(type))
            return obj;

        if (isParsable(type))
            try {
                IFormatter<Object> formatter = Typers.getTyper(type, IFormatter.class);
                if (formatter != null) {
                    String text = formatter.format(obj);
                    if (text == null)
                        throw new NullPointerException("formatted text");
                    return text;
                }
            } catch (QueryException e) {
                // not formattable, ignored.
            }

        return null;
    }

    static boolean isParsable(Class<?> type) {
        try {
            IParser<?> parser = Typers.getTyper(type, IParser.class);
            if (parser != null)
                return true;
        } catch (QueryException e) {
            logger.error("Failed to get parser of " + type, e);
            return false;
        }
        return false;
    }

    public static String encodeExcluded(String reason) {
        StringBuilder sb = new StringBuilder();
        sb.append("<excluded");
        if (reason != null)
            sb.append(":");
        sb.append(reason);
        sb.append(">");
        return sb.toString();
    }

}
