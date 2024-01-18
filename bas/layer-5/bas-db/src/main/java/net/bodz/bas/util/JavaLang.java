package net.bodz.bas.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class JavaLang {

    static final String NULL_CONST = "null";

    public static String toJavaLiteral(Object o, int maxLen) {
        if (o == null)
            return NULL_CONST;
        int typeId = TypeKind.getTypeId(o.getClass());
        switch (typeId) {
        case TypeId.BYTE:
        case TypeId.SHORT:
        case TypeId.INTEGER:
        case TypeId.LONG:

        case TypeId.FLOAT:
        case TypeId.DOUBLE:
            return o.toString();

        case TypeId.CLASS:
            Class<?> c = (Class<?>) o;
            return c.getSimpleName() + ".class";

        case TypeId.THROWABLE:
        case TypeId.EXCEPTION:
            Throwable t = (Throwable) o;
            return String.format("Error(%s, \"%s\")", //
                    t.getClass().getSimpleName(), t.getMessage());

        case TypeId.BOOLEAN:
            Boolean b = (Boolean) o;
            return b ? "t" : "f";

        case TypeId.ENUM:
        case TypeId.BIG_INTEGER:
        case TypeId.BIG_DECIMAL:
            return o.toString();

        case TypeId.CHARACTER:
            String charStr = o.toString();
            return StringQuote.qJavaString(charStr);

        case TypeId.DATE:
        case TypeId.SQL_DATE:
        case TypeId.CALENDAR:
            String s = o.toString();
            return StringQuote.qqJavaString(s);

        case TypeId.INSTANT:
        case TypeId.ZONED_DATE_TIME:
        case TypeId.OFFSET_DATE_TIME:
        case TypeId.LOCAL_DATE_TIME:
        case TypeId.LOCAL_DATE:
        case TypeId.LOCAL_TIME:
            IVariantConvertContext context = VariantConvertContexts.getContext();
            String format = null;
            switch (typeId) {
            case TypeId.INSTANT:
                format = context.formatInstant((Instant) o);
                break;
            case TypeId.ZONED_DATE_TIME:
                format = context.formatZonedDateTime((ZonedDateTime) o);
                break;
            case TypeId.OFFSET_DATE_TIME:
                format = context.formatOffsetDateTime((OffsetDateTime) o);
                break;
            case TypeId.LOCAL_DATE_TIME:
                format = context.formatLocalDateTime((LocalDateTime) o);
                break;
            case TypeId.LOCAL_DATE:
                format = context.formatLocalDate((LocalDate) o);
                break;
            case TypeId.LOCAL_TIME:
                format = context.formatLocalTime((LocalTime) o);
                break;
            }
            return StringQuote.qqJavaString(format);

        case TypeId.STRING:
        default:
            String str = o.toString();
            str = Strings.ellipsis(o.toString(), maxLen);
            return StringQuote.qqJavaString(str);
        }
    }

}
