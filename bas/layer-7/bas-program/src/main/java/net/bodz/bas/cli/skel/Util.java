package net.bodz.bas.cli.skel;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;

class Util {

    public static <T> T addarray(Class<?> type, T fieldobj, Object val) {
        Class<?> valtype = type.getComponentType();
        int index;
        if (fieldobj == null) {
            fieldobj = (T) Array.newInstance(valtype, 1);
            index = 0;
        } else {
            int len = Array.getLength(fieldobj);
            T newarray = (T) Array.newInstance(valtype, len + 1);
            System.arraycopy(fieldobj, 0, newarray, 0, len);
            fieldobj = newarray;
            index = len;
        }
        Array.set(fieldobj, index, val);
        return fieldobj;
    }

    @SuppressWarnings("unchecked")
    public static <T> T addmulti(Class<?> type, T fieldobj, Object val)
            throws InstantiationException, IllegalAccessException {
        if (type.isArray())
            return addarray(type, fieldobj, val);
        if (fieldobj == null) {
            if (type.isInterface() || Modifier.isAbstract(type.getModifiers())) {
                if (List.class.isAssignableFrom(type))
                    type = ArrayList.class;
                else if (SortedSet.class.isAssignableFrom(type))
                    type = TreeSet.class;
                else if (Set.class.isAssignableFrom(type))
                    type = HashSet.class;
                else if (Collection.class.isAssignableFrom(type))
                    type = ArrayList.class;
                else if (SortedMap.class.isAssignableFrom(type))
                    type = TreeMap.class;
                else if (Map.class.isAssignableFrom(type))
                    type = HashMap.class;
                else
                    throw new IllegalUsageError("don\'t know how to create new instance of abstract class/interface "
                            + type);
            }
            fieldobj = (T) type.newInstance();
        }
        if (fieldobj instanceof Collection) {
            ((Collection<Object>) fieldobj).add(val);
        } else if (fieldobj instanceof Map) {
            Entry<Object, Object> entry = (Entry<Object, Object>) val;
            ((Map<Object, Object>) fieldobj).put(entry.getKey(), entry.getValue());

        }
        return fieldobj;
    }

    public static IParser<?> guessParser(IParser<?> parser, Class<?> clazz)
            throws ParseException {
        if (parser == null)
            parser = Traits.getTrait(clazz, IParser.class);
        return parser;
    }

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
