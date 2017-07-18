package net.bodz.lily.util.ajax;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;

public class JsonValueFormat {

    public static Object format(Class<?> type, String fmt, Object val) {
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
        return val;
    }

}
