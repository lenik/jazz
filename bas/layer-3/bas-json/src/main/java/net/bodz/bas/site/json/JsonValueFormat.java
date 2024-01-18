package net.bodz.bas.site.json;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;

public class JsonValueFormat {

    /**
     * Convert value to specific format.
     *
     * @param fmt
     *            A SimpleDateFormat format string for Date or Time type.
     *
     *            A DecimalFormat format string for numeric type.
     */
    public static Object format(Class<?> type, String fmt, Object val) {
        if (fmt == null)
            throw new NullPointerException("fmt");
        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId.DATE:
        case TypeId.SQL_DATE:
            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            return sdf.format(val);

        case TypeId.INSTANT:
        case TypeId.ZONED_DATE_TIME:
        case TypeId.OFFSET_DATE_TIME:
        case TypeId.LOCAL_DATE_TIME:
        case TypeId.LOCAL_DATE:
        case TypeId.LOCAL_TIME:
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fmt);
            return formatter.format((TemporalAccessor) val);
        }

        if (val instanceof Number) {
            DecimalFormat decimalFormat = new DecimalFormat(fmt);
            return decimalFormat.format(val);
        }

        // non-primitives.
//
//        switch (fmt) {
//        case "json":
//            if (val instanceof IJsonSerializable) {
//                IJsonSerializable js = (IJsonSerializable) val;
//            }
//        }
        return val;
    }

}
