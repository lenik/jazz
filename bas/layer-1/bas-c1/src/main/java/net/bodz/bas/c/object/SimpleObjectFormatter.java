package net.bodz.bas.c.object;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class SimpleObjectFormatter {

    public static String dispval(Object obj) {
        if (obj == null)
            return "null";
        Class<?> type = obj.getClass();
        StringBuilder buf = null;
        if (type.isArray()) {
            int len = Array.getLength(obj);
            buf = new StringBuilder(len * 20);
            buf.append("{");
            for (int i = 0; i < len; i++) {
                if (i > 0)
                    buf.append(", ");
                buf.append(dispval(Array.get(obj, i)));
            }
            buf.append("}");
        } else if (obj instanceof Collection<?>) {
            Collection<?> col = (Collection<?>) obj;
            buf = new StringBuilder(col.size() * 20);
            buf.append(type.getSimpleName() + " {");
            boolean first = true;
            for (Object c : col) {
                if (first)
                    first = false;
                else
                    buf.append(", ");
                buf.append(dispval(c));
            }
            buf.append('}');
        } else if (obj instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) obj;
            buf = new StringBuilder(map.size() * 20);
            buf.append(type.getSimpleName() + " {");
            boolean first = true;
            for (Map.Entry<?, ?> e : map.entrySet()) {
                if (first)
                    first = false;
                else
                    buf.append(", ");
                buf.append(e.getKey() + "=" + dispval(e.getValue()));
            }
            buf.append('}');
        } else if (obj instanceof String) {
            return "\"" + obj + "\"";
        } else {
            String typeName = obj.getClass().getName();
            int dot = typeName.lastIndexOf('.');
            if (dot != -1)
                typeName = typeName.substring(dot + 1);
            return typeName + "(" + String.valueOf(obj) + ")";
        }
        return buf == null ? "" : buf.toString();
    }

}
