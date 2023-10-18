package net.bodz.bas.util;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;

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
        case TypeId.JODA_DATETIME:
        case TypeId.CALENDAR:
            String s = o.toString();
            return StringQuote.qqJavaString(s);

        case TypeId.STRING:
        default:
            String str = o.toString();
            str = Strings.ellipsis(o.toString(), maxLen);
            return StringQuote.qqJavaString(str);
        }
    }

}
