package net.bodz.bas.site.json;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;

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

        case TypeId.JODA_DATETIME:
            DateTime dateTime = (DateTime) val;
            return dateTime.toString(fmt);
        }

        if (val instanceof Number) {
            DecimalFormat dfmt = new DecimalFormat(fmt);
            return dfmt.format(val);
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
